import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge path during UTF8 write
        bv.putUTF8("\u0800"); // 3-byte UTF-8 sequence, charLength=1 but byteLength=3

        assertEquals(5, bv.length); // 2 length bytes + 3 data bytes
        assertArrayEquals(new byte[] {0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, bv.data);
    }
}