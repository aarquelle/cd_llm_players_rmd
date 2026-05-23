import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putUTF8("A\u0080\u0800");

        assertEquals(8, v.length);

        byte[] expectedPrefix = new byte[] {
                0, 6, 65, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        };
        assertArrayEquals(expectedPrefix, java.util.Arrays.copyOf(v.data, v.length));
    }
}