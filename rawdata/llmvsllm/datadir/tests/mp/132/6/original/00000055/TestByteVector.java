import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);

        byte[] src = new byte[] { 9, 8, 7, 6 };
        v.putByteArray(src, 1, 2); // copies 8,7

        int beforeNull = v.length;
        v.putByteArray(null, 123, 3); // should just advance length by 3 and possibly enlarge

        assertArrayEquals(new byte[] { 0x7F, 8, 7 }, java.util.Arrays.copyOfRange(v.data, 0, 3));
        assertEquals(beforeNull + 3, v.length);
    }
}