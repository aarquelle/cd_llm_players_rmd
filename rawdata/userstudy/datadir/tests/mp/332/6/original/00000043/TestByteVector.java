import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x12);              // length=1, data.length=1
        bv.putByteArray(null, 0, 3);   // triggers enlarge(3): length2=4, length1=2 -> new length must be 4

        assertAll(
                () -> assertEquals(4, bv.data.length),
                () -> assertEquals((byte) 0x12, bv.data[0])
        );
    }
}