import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55).putUTF8("A\u20ACB"); // 'A' + Euro(3 bytes) + 'B' => 5 bytes, length prefix = 0x0005

        assertEquals(1 + 2 + 5, v.length);

        byte[] expected = new byte[] {
            0x55,
            0x00, 0x05,
            0x41,
            (byte) 0xE2, (byte) 0x82, (byte) 0xAC,
            0x42
        };
        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}