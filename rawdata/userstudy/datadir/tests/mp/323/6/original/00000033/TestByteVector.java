import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x11).putByte(0x22);
        bv.putByteArray(new byte[] { 0x33, 0x44, 0x55 }, 0, 3);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertEquals(5, data.length);
        assertArrayEquals(new byte[] { 0x11, 0x22, 0x33, 0x44, 0x55 }, data);
    }
}