import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F); // offset to ensure prefix write-back uses original 'length' correctly

        bv.putUTF8("A\u0080\u0800"); // 1-byte, 2-byte, 3-byte => total 6 bytes

        assertEquals(1 + 2 + 6, bv.length);

        int prefixIndex = 1;
        assertEquals(6, ((bv.data[prefixIndex] & 0xFF) << 8) | (bv.data[prefixIndex + 1] & 0xFF));
    }
}