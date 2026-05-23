import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(16);
        v.putUTF8("\u20AC");

        Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int len = (Integer) lengthField.get(v);
        assertEquals(5, len);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] d = (byte[]) dataField.get(v);
        assertArrayEquals(new byte[] { 0, 3, (byte) 0xE2, (byte) 0x82, (byte) 0xAC },
                new byte[] { d[0], d[1], d[2], d[3], d[4] });
    }
}