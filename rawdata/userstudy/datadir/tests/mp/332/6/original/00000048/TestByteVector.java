import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x7F).putShort(0xA1B2);

        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0xA1, (byte) 0xB2 }, v.data);
        assertEquals(3, v.length);
    }
}