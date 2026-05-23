import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x7F); // ensure length offset is non-zero
        bv.putUTF8("\u0000\u0800"); // NUL (2 bytes) + U+0800 (3 bytes) => 5 bytes, prefix should be 0x0005

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(bv);

        assertEquals(1 + 2 + 5, len);
        assertArrayEquals(
                new byte[] { 0x00, 0x05, (byte) 0xC0, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                new byte[] { data[1], data[2], data[3], data[4], data[5], data[6], data[7] }
        );
    }
}