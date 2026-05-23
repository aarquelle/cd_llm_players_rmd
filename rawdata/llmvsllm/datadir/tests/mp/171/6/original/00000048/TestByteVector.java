import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => utfLen = 4, total = 2 + 4 = 6

        assertEquals(6, v.length);

        int encoded = ((v.data[2] & 0xFF) << 24)
                | ((v.data[3] & 0xFF) << 16)
                | ((v.data[4] & 0xFF) << 8)
                | (v.data[5] & 0xFF);
        assertEquals(0x41E0A080, encoded);
    }
}