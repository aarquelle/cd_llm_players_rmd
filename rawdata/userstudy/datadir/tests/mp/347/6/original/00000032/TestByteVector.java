import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge when writing 4 bytes
        bv.putInt(0x80FF0102);

        assertEquals(0x80FF0102, ((bv.data[0] & 0xFF) << 24) | ((bv.data[1] & 0xFF) << 16) | ((bv.data[2] & 0xFF) << 8) | (bv.data[3] & 0xFF));
        assertEquals(4, bv.length);
    }
}