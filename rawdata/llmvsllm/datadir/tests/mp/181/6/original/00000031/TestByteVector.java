import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                // "A" (1 byte) + U+0080 (2 bytes) + U+0800 (3 bytes) + "z" (1 byte) => 7 bytes
        String s = "A" + '\u0080' + '\u0800' + "z";

        ByteVector bv = new ByteVector(1); // force enlargement paths to run
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x07,                         // utf byte length
                0x41,                               // 'A'
                (byte) 0xC2, (byte) 0x80,           // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                0x7A                                // 'z'
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}