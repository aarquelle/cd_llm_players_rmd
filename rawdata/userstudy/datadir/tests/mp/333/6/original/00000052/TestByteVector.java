import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        Object returned = bv.put11(0xAB, 0xCD);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        java.lang.reflect.Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int len = (int) lengthField.get(bv);

        assertSame(bv, returned);
        assertEquals("len=" + len + ", b0=" + (data[0] & 0xFF) + ", b1=" + (data[1] & 0xFF),
                "2:171:205", len + ":" + (data[0] & 0xFF) + ":" + (data[1] & 0xFF));
    }
}