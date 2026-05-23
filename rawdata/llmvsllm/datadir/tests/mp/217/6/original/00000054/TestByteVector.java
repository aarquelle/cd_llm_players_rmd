import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putUTF8("A\u0080\u0800"); // expected UTF-8 bytes: 41 C2 80 E0 A0 80, length=6

        assertEquals(8, v.length);

        int combined =
                (((v.data[0] & 0xFF) << 24) |
                 ((v.data[1] & 0xFF) << 16) |
                 ((v.data[2] & 0xFF) << 8)  |
                 (v.data[3] & 0xFF)) ^
                (((v.data[4] & 0xFF) << 24) |
                 ((v.data[5] & 0xFF) << 16) |
                 ((v.data[6] & 0xFF) << 8)  |
                 (v.data[7] & 0xFF));

        int expected =
                ((0x00 << 24) | (0x06 << 16) | (0x41 << 8) | 0xC2) ^
                ((0x80 << 24) | (0xE0 << 16) | (0xA0 << 8) | 0x80);

        assertEquals(expected, combined);
    }
}