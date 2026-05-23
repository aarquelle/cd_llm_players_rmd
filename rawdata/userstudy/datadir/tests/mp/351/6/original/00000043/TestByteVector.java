import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(10);
        bv.putUTF8("Hi");

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        Field lengthF = ByteVector.class.getDeclaredField("length");
        lengthF.setAccessible(true);
        int length = (int) lengthF.get(bv);

        assertArrayEquals(new byte[] { 0, 2, 'H', 'i' }, Arrays.copyOf(data, 4));
        assertEquals(4, length);
    }
}