import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u20ACB"); // 'A' (1) + '€' (3) + 'B' (1) => 5 bytes, length prefix should be 0x0005

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int length = (Integer) lenF.get(bv);

        assertEquals(7, length); // 2 bytes length prefix + 5 bytes payload
        assertArrayEquals(new byte[] {0, 5, 0x41, (byte) 0xE2, (byte) 0x82, (byte) 0xAC, 0x42},
                new byte[] {data[0], data[1], data[2], data[3], data[4], data[5], data[6]});
    }
}