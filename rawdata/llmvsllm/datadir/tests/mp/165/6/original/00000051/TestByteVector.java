import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("A\u0000\u0080\u0800"); // 1 + 2 + 2 + 3 = 8 bytes of UTF-8 payload

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int length = (Integer) lenF.get(bv);

        assertEquals(10, length); // 2-byte length prefix + 8-byte payload
        assertArrayEquals(new byte[] {0, 8, 0x41, (byte) 0xC0, (byte) 0x80, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80},
                java.util.Arrays.copyOf(data, length));
    }
}