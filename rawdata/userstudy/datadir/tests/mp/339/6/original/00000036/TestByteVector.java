import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        ByteVector returned = v.put11(0xAA, 0xBB);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(v);

        java.lang.reflect.Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int len = (int) lengthField.get(v);

        assertSame(v, returned);
        assertEquals("len=" + len + ", b0=" + data[0] + ", b1=" + data[1], 0xAABB, ((data[0] & 0xFF) << 8) | (data[1] & 0xFF));
    }
}