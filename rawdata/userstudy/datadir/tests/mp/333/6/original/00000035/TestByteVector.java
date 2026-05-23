import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        v.putByte(0x11);
        v.putByteArray(null, 0, 2);
        byte[] src = new byte[] { 0x55, 0x66, 0x77, 0x12 };
        v.putByteArray(src, 1, 2);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(v);

        Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int length = (int) lengthField.get(v);

        assertEquals(5, length);
        assertArrayEquals(new byte[] { 0x11, 0x00, 0x00, 0x66, 0x77 }, Arrays.copyOf(data, length));
    }
}