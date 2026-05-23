import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0xAB).putByte(0xCD);

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(v);
        int len = (int) lenF.get(v);

        assertArrayEquals(new byte[] {(byte) 0xAB, (byte) 0xCD}, java.util.Arrays.copyOf(data, len));
        assertTrue(data.length >= 2);
    }
}