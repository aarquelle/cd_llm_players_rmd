import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u0080\u07FF\u0800\u20ACZ";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new DataOutputStream(baos).writeUTF(s);
        byte[] expected = baos.toByteArray();

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        byte[] actual = Arrays.copyOf(bv.data, bv.length);

        assertArrayEquals(expected, actual);
        assertEquals(expected.length, bv.length);
    }
}