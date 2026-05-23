import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x55);
        v.putUTF8("\u0080");

        int packed =
                ((v.data[0] & 0xFF) << 24) |
                ((v.data[1] & 0xFF) << 16) |
                ((v.data[2] & 0xFF) << 8) |
                (v.data[3] & 0xFF);

        assertEquals(5, v.length);
        assertEquals(0x550002C2, packed);
    }
}