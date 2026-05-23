import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        byte[] src = new byte[] { 7, 8, 9, 10 };
        v.putByteArray(src, 1, 2);

        assertEquals(2, v.length);
        assertEquals(8, v.data[0] & 0xFF);
    }
}