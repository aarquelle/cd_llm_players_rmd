import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u00A2\u0800B"; // includes NUL (2 bytes), 2-byte, 3-byte, and ASCII chars

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new DataOutputStream(bos).writeUTF(s);
        byte[] expected = bos.toByteArray();

        ByteVector bv = new ByteVector(1); // force enlargement path
        bv.putUTF8(s);

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}