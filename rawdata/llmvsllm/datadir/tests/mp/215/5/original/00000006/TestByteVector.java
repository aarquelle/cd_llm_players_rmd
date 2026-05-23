import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(1).putByte(2);
        v.putByteArray(new byte[] {3, 4, 5}, 0, 3);

        assertEquals(5, v.length);
        assertArrayEquals(new byte[] {1, 2, 3, 4, 5}, v.data);
    }
}