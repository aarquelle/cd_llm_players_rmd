import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x12);

        byte[] payload = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        bv.putByteArray(payload, 0, payload.length);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertEquals(11, data.length);
        assertArrayEquals(payload, java.util.Arrays.copyOfRange(data, 1, 11));
    }
}