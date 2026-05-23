import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putUTF8("A\u0800");

        assertArrayEquals(new byte[] {0, 4, 65, (byte) 0xE0, (byte) 0xA0, (byte) 0x80}, v.data);
        assertEquals(6, v.length);
    }
}