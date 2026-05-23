import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("a\u0800"); // 'a' (1 byte) + U+0800 (3 bytes) => byteLength=4

        assertEquals(6, bv.length); // 2 bytes length prefix + 4 bytes UTF-8 payload

        int payloadLen = ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF);
        int payloadSig = ((bv.data[2] & 0xFF) << 24)
                | ((bv.data[3] & 0xFF) << 16)
                | ((bv.data[4] & 0xFF) << 8)
                | (bv.data[5] & 0xFF);
        assertEquals((4 << 24) | (0x61 << 16) | (0xE0 << 8) | 0xA0, (payloadLen << 24) | (payloadSig >>> 8));
    }
}