import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putByte(0x55).putUTF8("é").putByte(0x66);

        assertArrayEquals(new byte[] { 0x55, 0x00, 0x02, (byte) 0xC3, (byte) 0xA9, 0x66 }, v.data);
        assertEquals(6, v.length);
    }
}