import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "a\u0001\u007F\u0080\u07FF\u0800\u20ACz";
        ByteVector bv = new ByteVector(2);
        bv.putUTF8(s);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new DataOutputStream(baos).writeUTF(s);
        byte[] expected = baos.toByteArray();

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(bv);
        int len = (Integer) lenF.get(bv);

        byte[] actual = java.util.Arrays.copyOf(data, len);

        assertEquals(expected.length, len);
        assertArrayEquals(expected, actual);
    }
}