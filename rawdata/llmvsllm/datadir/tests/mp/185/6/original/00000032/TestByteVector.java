import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0000\u07FF\u0800\u1234\u0001Z";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF(s);
        dos.flush();
        byte[] expected = bos.toByteArray();

        ByteVector bv = new ByteVector(1);
        bv.putByte(0x55);
        int start = bv.length;
        bv.putUTF8(s);

        byte[] actual = Arrays.copyOfRange(bv.data, start, bv.length);

        assertArrayEquals(expected, actual);
        assertEquals(start + expected.length, bv.length);
    }
}