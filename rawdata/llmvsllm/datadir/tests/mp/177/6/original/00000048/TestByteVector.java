import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        String s = "A\u00A9\u20AC";

        byte[] expected = s.getBytes("UTF-8");

        v.putUTF8(s);

        byte[] actual = new byte[v.length - 2];
        actual[0] = v.data[2];
        actual[1] = v.data[3];
        actual[2] = v.data[4];
        actual[3] = v.data[5];
        actual[4] = v.data[6];
        actual[5] = v.data[7];

        assertEquals(expected.length + 2, v.length);
        assertArrayEquals(expected, actual);
    }
}