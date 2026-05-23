import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);

        java.lang.reflect.Method m = ByteVector.class.getDeclaredMethod("put12", int.class, int.class);
        m.setAccessible(true);
        m.invoke(bv, 0x12, 0x3456);

        assertEquals(3, bv.data.length);
        assertEquals((byte) 0x12, bv.data[0]);
    }
}