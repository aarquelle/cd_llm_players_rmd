import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        byte[] src = new byte[] { 10, 20, 30, 40 };

        ByteVector returned = v.putByteArray(src, 1, 3);

        assertSame(v, returned);
        assertEquals(3, v.length);
    }
}