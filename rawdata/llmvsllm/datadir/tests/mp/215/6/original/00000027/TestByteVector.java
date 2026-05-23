import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge on putInt
        v.putByte(0x7F);
        v.putInt(0x01020304);

        assertAll(
                () -> assertArrayEquals(new byte[] { (byte) 0x7F, 0x01, 0x02, 0x03, 0x04 }, java.util.Arrays.copyOf(v.data, v.length)),
                () -> assertEquals(5, v.length)
        );
    }
}