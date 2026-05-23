import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putInt(0x11223344); // fills data[0..3], length=4

        java.lang.reflect.Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        lengthField.setInt(v, 2); // corrupt length so enlarge should only copy 2 bytes

        try {
            v.putInt(0x55667788); // triggers enlarge; buggy version copies 4 bytes into 2-sized array -> AIOOBE
        } catch (ArrayIndexOutOfBoundsException ex) {
            fail("enlarge should not copy beyond current length");
        }

        assertEquals(6, v.length);
    }
}