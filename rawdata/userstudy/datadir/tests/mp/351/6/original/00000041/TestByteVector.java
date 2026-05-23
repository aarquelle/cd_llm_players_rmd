import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(8);
        bv.putLong(0x0102030405060708L);

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        Field lengthF = ByteVector.class.getDeclaredField("length");
        lengthF.setAccessible(true);
        int length = (Integer) lengthF.get(bv);

        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, Arrays.copyOf(data, 8));
        assertEquals(8, length);
    }
}