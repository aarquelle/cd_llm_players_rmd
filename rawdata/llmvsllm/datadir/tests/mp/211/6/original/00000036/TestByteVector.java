import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1); // force enlarge(3) path on first put12
        bv.put12(0xAB, 0x1234);

        assertAll(
                () -> assertArrayEquals(new byte[] { (byte) 0xAB, 0x12, 0x34 }, java.util.Arrays.copyOf(bv.data, bv.length)),
                () -> assertEquals(3, bv.length)
        );
    }
}