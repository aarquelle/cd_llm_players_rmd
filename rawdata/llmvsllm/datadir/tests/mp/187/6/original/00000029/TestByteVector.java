import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u007F\u0080\u07FF\u0800Z";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new DataOutputStream(baos).writeUTF(s);
        byte[] expected = baos.toByteArray();

        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}