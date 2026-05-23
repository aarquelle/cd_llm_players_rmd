import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x01);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);

        v.putByte(0x02);

        byte[] data = (byte[]) dataField.get(v);
        assertEquals(2, data.length);
    }
}