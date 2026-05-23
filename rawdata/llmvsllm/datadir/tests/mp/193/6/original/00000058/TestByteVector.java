import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);

        v.putByte(0x7F).putUTF8("A\u0800B");

        assertEquals(1 + 2 + 5, v.length);
        assertArrayEquals(new byte[] {
                (byte) 0x7F,
                0x00, 0x05,
                0x41,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x42
        }, java.util.Arrays.copyOf(v.data, v.length));
    }
}