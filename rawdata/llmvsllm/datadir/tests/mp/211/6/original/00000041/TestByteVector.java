import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3); // force enlarge in putUTF8 for multi-byte
        v.putByte(0x7F);
        v.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => utfLen=4, total added=6

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] d = (byte[]) dataF.get(v);

        assertEquals(8, v.length); // 1 + 2 + 4
        assertArrayEquals(new byte[] { 0x7F, 0x00, 0x04, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, java.util.Arrays.copyOf(d, 7));
    }
}