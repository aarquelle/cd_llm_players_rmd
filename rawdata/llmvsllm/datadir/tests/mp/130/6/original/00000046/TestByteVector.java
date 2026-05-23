import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.put11(0xAB, 0xCD);

        assertArrayEquals(new byte[] { (byte) 0xAB, (byte) 0xCD }, java.util.Arrays.copyOf(v.data, v.length));
        assertEquals(2, v.length);
    }
}