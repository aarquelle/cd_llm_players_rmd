import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);

        bv.putByte(0x11).putUTF8("A\u0800B").putByte(0x22);

        assertArrayEquals(
                new byte[] {
                        0x11,
                        0x00, 0x05, // UTF length = 5
                        0x41,       // 'A'
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                        0x42,       // 'B'
                        0x22
                },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
    }
}