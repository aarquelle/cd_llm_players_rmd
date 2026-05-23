import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);

        v.putUTF8("\u0080\u0800"); // 2 chars -> UTF8 byte length 5, forces re-computation and enlarge

        int lenAfterUtf = v.length;
        v.putByteArray(null, 0, 3); // appends 3 zero bytes

        assertArrayEquals(
                new byte[] { 0x00, 0x05, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x00, 0x00, 0x00 },
                java.util.Arrays.copyOf(v.data, v.length)
        );
        assertEquals(lenAfterUtf + 3, v.length);
    }
}