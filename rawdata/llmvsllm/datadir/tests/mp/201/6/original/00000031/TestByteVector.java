import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u00A2\u20AC\u07FF\u0800Z";
        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF(s);
        dos.flush();
        byte[] expected = bos.toByteArray();

        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
        assertEquals(expected.length, bv.length);
    }
}