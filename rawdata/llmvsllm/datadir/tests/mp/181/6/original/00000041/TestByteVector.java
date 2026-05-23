import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);

        bv.putUTF8("\u0080\u0800"); // 2 chars -> 2 + (2 + 3) = 7 bytes total, forces non-ASCII path and enlarge

        assertArrayEquals(new byte[] { 0, 5, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                java.util.Arrays.copyOf(bv.data, bv.length));

        bv.putLong(0x0102030405060708L);

        assertArrayEquals(new byte[] { 0, 5, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                1, 2, 3, 4, 5, 6, 7, 8 }, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}