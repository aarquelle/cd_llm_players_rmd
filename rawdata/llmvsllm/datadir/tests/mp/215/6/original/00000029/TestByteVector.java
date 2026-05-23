import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0001\u007F\u0080\u07FF\u0800\u20ACZ";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeUTF(s);
        }
        byte[] expected = bos.toByteArray();

        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        byte[] actual = Arrays.copyOf(bv.data, bv.length);

        assertEquals(expected.length, actual.length);
        assertArrayEquals(expected, actual);
    }
}