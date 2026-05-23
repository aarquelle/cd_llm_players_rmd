import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        bv.putUTF8("A\u0000\u0800B"); // forces general UTF8 path: includes NUL (2 bytes) and > 0x07FF (3 bytes)
        int afterUtfLen = bv.length;

        bv.putLong(0x0102030405060708L);

        assertEquals(afterUtfLen + 8, bv.length);

        int start = afterUtfLen;
        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }, new byte[] {
                bv.data[start],
                bv.data[start + 1],
                bv.data[start + 2],
                bv.data[start + 3],
                bv.data[start + 4],
                bv.data[start + 5],
                bv.data[start + 6],
                bv.data[start + 7]
        });
    }
}