import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putShort(0xABCD);
        bv.putShort(0x0102);

        assertArrayEquals(new byte[] {(byte) 0xAB, (byte) 0xCD, (byte) 0x01, (byte) 0x02}, bv.data);
        assertEquals(4, bv.length);
    }
}