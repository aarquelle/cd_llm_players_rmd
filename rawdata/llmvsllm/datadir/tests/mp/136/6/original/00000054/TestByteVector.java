import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u07FF\u0800B";
        ByteVector v = new ByteVector(2);
        v.putUTF8(s);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF(s);
        dos.flush();
        byte[] expected = bos.toByteArray();

        assertArrayEquals(expected, java.util.Arrays.copyOf(v.data, v.length));
    }
}