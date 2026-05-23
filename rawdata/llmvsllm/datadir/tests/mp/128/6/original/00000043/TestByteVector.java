import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge during putUTF8
        bv.putUTF8("A\u0080\u0800"); // 1-byte, 2-byte, 3-byte UTF-8

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        java.lang.reflect.Field lengthF = ByteVector.class.getDeclaredField("length");
        lengthF.setAccessible(true);
        int length = (Integer) lengthF.get(bv);

        assertEquals(10, length); // 2 length bytes + (1 + 2 + 3) bytes = 8 total? actually payload 6 => total 8; but includes 2 + 6 = 8
        assertArrayEquals(new byte[] {
                0x00, 0x06,                  // UTF length in bytes
                0x41,                        // 'A'
                (byte) 0xC2, (byte) 0x80,    // U+0080
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOf(data, length));
    }
}