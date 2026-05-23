import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F).putUTF8("\u0080\u0800"); // forces general UTF8 encoding + enlarge

        assertArrayEquals(new byte[] {
                0x7F,
                0x00, 0x05,               // UTF8 byte length: 2 + 3 = 5
                (byte) 0xC2, (byte) 0x80, // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOf(v.data, v.length));
    }
}