import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u0080\u07FF\u0800Z";

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(s);
        dos.flush();
        byte[] expected = baos.toByteArray();

        assertArrayEquals(expected, java.util.Arrays.copyOf(bv.data, bv.length));
        assertTrue(bv.data.length > 2);
    }
}