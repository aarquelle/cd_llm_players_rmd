import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector(1);
    bv.putByte(1);
    int newSize = 10;
    // Use reflection to access the private method enlarge
    try {
        java.lang.reflect.Method enlargeMethod = ByteVector.class.getDeclaredMethod("enlarge", int.class);
        enlargeMethod.setAccessible(true);
        enlargeMethod.invoke(bv, newSize);
    } catch (Exception e) {
        fail("Reflection to access private method failed: " + e);
    }
    assertTrue(bv.data.length >= newSize);
    }
}