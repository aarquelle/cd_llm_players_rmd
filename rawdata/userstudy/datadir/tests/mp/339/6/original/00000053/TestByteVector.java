import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F);
        bv.putShort(0x1234);

        assertArrayEquals(new byte[] { (byte) 0x7F, 0x12, 0x34 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}