import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putUTF8("A\u0000\u00A2\u0800"); // A, NUL, ¢, U+0800

        assertEquals(new byte[] {
                0x00, 0x09, // UTF-8 byte length = 1 + 2 + 2 + 3 = 8? Actually: 'A'(1) + NUL(2) + ¢(2) + U+0800(3) = 8, plus length header uses 2 bytes => total 10; header stores 8 => 0x0008
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xC2, (byte) 0xA2,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, java.util.Arrays.copyOf(bv.data, bv.length));

        assertEquals(10, bv.length);
    }
}