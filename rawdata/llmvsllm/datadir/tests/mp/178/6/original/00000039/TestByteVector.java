import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800B"); // 'A' (1 byte) + U+0800 (3 bytes) + 'B' (1 byte) => 5 bytes

        assertEquals(7, bv.length); // 2-byte length prefix + 5 bytes payload

        int payloadLen = ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF);
        assertEquals(5, payloadLen);
    }
}