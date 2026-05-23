import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        ByteVector ret = v.put12(0xAB, 0xCDEF);

        assertSame(v, ret);
        assertArrayEquals(new byte[]{(byte) 0xAB, (byte) 0xCD, (byte) 0xEF}, v.data);
    }
}