import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x7F);

        String s = "A" + "\u0000" + "\u0800";
        v.putUTF8(s);

        v.putByte(0x55);

        assertArrayEquals(
                new byte[] {
                        0x7F,
                        0x00, 0x06,
                        0x41,
                        (byte) 0xC0, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                        0x55
                },
                java.util.Arrays.copyOf(v.data, v.length)
        );
    }
}