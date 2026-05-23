import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F).putInt(0x89ABCDEF);

        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }, v.data);
        assertEquals(5, v.length);
    }
}