import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x55);
        bv.put11(0xAA, 0xCC);

        assertArrayEquals(new byte[] { 0x55, (byte) 0xAA, (byte) 0xCC }, bv.data);
    }
}