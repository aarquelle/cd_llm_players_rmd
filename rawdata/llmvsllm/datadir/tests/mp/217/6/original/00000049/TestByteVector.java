import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);

        // "Aé€" => UTF-8 bytes: 41 C3 A9 E2 82 AC (6 bytes), header should be 00 06
        v.putUTF8("A\u00E9\u20AC");

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(v);

        assertEquals(8, len);
        assertArrayEquals(new byte[] {0, 6, 0x41, (byte) 0xC3, (byte) 0xA9, (byte) 0xE2, (byte) 0x82, (byte) 0xAC},
                new byte[] {data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]});
    }
}