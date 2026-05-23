import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4); // force potential enlarge path
        bv.putUTF8("A\u0800"); // 'A' (1 byte) + U+0800 (3 bytes) => byteLength = 4

        assertEquals(6, bv.length); // 2 bytes length prefix + 4 bytes payload

        byte[] d = bv.data;
        assertEquals((byte) 0xE0, d[3]); // first byte of 3-byte UTF-8 encoding for U+0800
    }
}