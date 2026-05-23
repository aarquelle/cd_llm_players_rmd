import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        ByteVector returned = v.putByte(0xAA).putByte(0xBB);

        assertSame(v, returned);
        assertArrayEquals(new byte[] { (byte) 0xAA, (byte) 0xBB }, java.util.Arrays.copyOf(v.data, v.length));
    }
}