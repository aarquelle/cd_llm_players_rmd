import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1); // force enlarge path
        long l = 0x0102030405060708L;

        Object returned = v.putLong(l);

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (int) lenF.get(v);

        assertArrayEquals(new byte[] {1,2,3,4,5,6,7,8}, java.util.Arrays.copyOf(data, 8));
        assertSame(v, returned);
    }
}