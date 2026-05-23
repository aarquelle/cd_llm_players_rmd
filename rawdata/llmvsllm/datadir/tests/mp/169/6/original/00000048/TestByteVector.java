import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0000\u0800");

        assertEquals(8, bv.length);

        int packed =
                ((bv.data[0] & 0xFF) << 24) |
                ((bv.data[1] & 0xFF) << 16) |
                ((bv.data[2] & 0xFF) << 8) |
                (bv.data[3] & 0xFF);

        assertEquals(0x000641C0, packed);
    }
}