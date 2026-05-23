import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "\u0800";
        ByteVector v = new ByteVector(4);
        v.putUTF8(s);

        byte[] expected = s.getBytes(Charset.forName("UTF-8"));

        assertEquals(2 + expected.length, v.length);
        assertArrayEquals(
                new byte[] { 0x00, (byte) expected.length, expected[0], expected[1], expected[2] },
                new byte[] { v.data[0], v.data[1], v.data[2], v.data[3], v.data[4] }
        );
    }
}