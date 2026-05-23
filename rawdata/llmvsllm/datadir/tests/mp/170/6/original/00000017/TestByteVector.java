import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0xAB);

        Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);

        assertEquals(1, lengthField.getInt(v));
        assertEquals((byte) 0xAB, ((byte[]) dataField.get(v))[0]);
    }
}