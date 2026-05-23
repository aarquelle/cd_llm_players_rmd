import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u0080\u07FF\u0800\u1234Z";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF(s);
        dos.flush();
        byte[] expected = bos.toByteArray();

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, Arrays.copyOf(bv.data, bv.length));
    }
}