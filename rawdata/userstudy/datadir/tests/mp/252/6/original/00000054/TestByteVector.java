import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putByte(0x55).putUTF8("A\u00A2\u20AC"); // "A", "¢"(2 bytes), "€"(3 bytes) => 6 bytes payload

        assertEquals(1 + 2 + 6, v.length);
        assertArrayEquals(
                new byte[] {
                        0x55,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC2, (byte) 0xA2,
                        (byte) 0xE2, (byte) 0x82, (byte) 0xAC
                },
                java.util.Arrays.copyOf(v.data, v.length)
        );
    }
}