import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u00E9\u0800";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new DataOutputStream(bos).writeUTF(s);
        byte[] expected = bos.toByteArray();

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);
        byte[] actual = java.util.Arrays.copyOf(bv.data, bv.length);

        assertArrayEquals(expected, actual);
        assertEquals(expected.length, bv.length);
    }
}