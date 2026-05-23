import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u007F\u0080\u07FF\u0800\u20AC";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new DataOutputStream(bos).writeUTF(s);
        byte[] expected = bos.toByteArray();

        ByteVector bv = new ByteVector(2); // force enlarge path
        bv.putUTF8(s);

        assertEquals(expected.length, bv.length, "length should match DataOutputStream.writeUTF byte count");
        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length), "encoded bytes should match writeUTF");
    }
}