import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x11);

        try {
            v.putByte(0x22);
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("putByte should enlarge when full");
        }

        java.lang.reflect.Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        assertEquals(2, lengthField.getInt(v));
    }
}