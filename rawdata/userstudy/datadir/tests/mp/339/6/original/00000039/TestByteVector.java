import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7E);              // length=1, capacity=2
        bv.putShort(0xABCD);           // requires enlarge (1+2 > 2), writes AB CD

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        assertArrayEquals(new byte[] { (byte) 0x7E, (byte) 0xAB, (byte) 0xCD }, java.util.Arrays.copyOf(data, 3));
        assertEquals(3, bv.length);
    }
}