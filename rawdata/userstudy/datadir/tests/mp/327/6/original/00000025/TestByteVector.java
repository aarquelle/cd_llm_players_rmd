import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x55); // force non-zero offset and enlargement on next put11
        bv.put11(0xAB, 0xCD);

        var dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        var lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int len = (int) lengthField.get(bv);

        assertArrayEquals(new byte[] { (byte) 0x55, (byte) 0xAB, (byte) 0xCD }, new byte[] { data[0], data[1], data[2] });
        assertEquals(3, len);
    }
}