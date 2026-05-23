import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0000\u07FF\u0800");

        assertArrayEquals(
                new byte[] {
                        0, 8,
                        0x41,
                        (byte) 0xC0, (byte) 0x80,
                        (byte) 0xDF, (byte) 0xBF,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                java.util.Arrays.copyOf(bv.data, bv.length)
        );
        assertEquals(10, bv.length);
    }
}