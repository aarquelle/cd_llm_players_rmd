import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x01).putByte(0xAB);

        var dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(v);

        assertEquals(2, v.length);
        assertArrayEquals(new byte[] { 0x01, (byte) 0xAB }, new byte[] { data[0], data[1] });
    }
}