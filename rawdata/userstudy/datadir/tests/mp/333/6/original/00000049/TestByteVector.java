import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        long value = 0x0102030405060708L;

        v.putLong(value);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(v);

        assertEquals(8, v.length);
        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, java.util.Arrays.copyOf(data, 8));
    }
}