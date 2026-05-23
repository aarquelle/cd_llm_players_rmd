import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.length = 1;
        v.data[0] = (byte) 0x55;

        ByteVector returned = v.put11(0xFE, 0x7F);

        assertEquals(v, returned);
        assertArrayEquals(new byte[] { (byte) 0x55, (byte) 0xFE, (byte) 0x7F }, new byte[] { v.data[0], v.data[1], v.data[2] });
    }
}