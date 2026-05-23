import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putUTF8("\u0080");

        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, Arrays.copyOf(v.data, v.length));
        assertEquals(4, v.length);
    }
}