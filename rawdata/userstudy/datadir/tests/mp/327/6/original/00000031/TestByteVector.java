import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByteArray(new byte[] {1, 2, 3}, 0, 3); // length=3, data.length=4

        bv.putByteArray(new byte[] {4, 5, 6, 7, 8, 9}, 0, 6); // triggers enlarge(6), required=9, double=8 => expect 9

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertEquals(9, data.length);
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, java.util.Arrays.copyOf(data, 9));
    }
}