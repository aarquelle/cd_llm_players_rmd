import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putUTF8("\u0080");

        assertEquals(4, v.length);
        assertEquals(0x0002C280, ((v.data[0] & 0xFF) << 24) | ((v.data[1] & 0xFF) << 16) | ((v.data[2] & 0xFF) << 8) | (v.data[3] & 0xFF));
    }
}