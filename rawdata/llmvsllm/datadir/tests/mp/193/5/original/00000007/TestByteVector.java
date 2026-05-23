import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByteArray(new byte[] {1, 2, 3, 4}, 0, 4);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);

        v.putByte(5); // triggers enlarge(1); should double from 4 to 8 in original
        byte[] data = (byte[]) dataField.get(v);

        assertEquals(8, data.length);
    }
}