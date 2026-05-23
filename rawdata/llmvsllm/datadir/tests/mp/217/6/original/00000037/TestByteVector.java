import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putShort(0xABCD);
        assertEquals(2, v.length);
        assertArrayEquals(new byte[] { (byte) 0xAB, (byte) 0xCD }, new byte[] { v.data[0], v.data[1] });
    }
}