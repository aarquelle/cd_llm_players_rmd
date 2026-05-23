import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);

        ByteVector ret1 = v.putByte(0xAB);
        ByteVector ret2 = v.putByte(0xCD);

        assertSame(v, ret2);
        assertArrayEquals(new byte[] { (byte) 0xAB, (byte) 0xCD }, new byte[] { v.data[0], v.data[1] });
    }
}