import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u0800B");

        assertEquals(7, v.length);

        byte[] d = v.data;
        assertArrayEquals(new byte[] {0, 5, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x42},
                new byte[] {d[0], d[1], d[2], d[3], d[4], d[5], d[6]});
    }
}