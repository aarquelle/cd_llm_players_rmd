import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        String s = "A\u0000\u00A2\u20AC"; // 'A'(1), NUL(2), '¢'(2), '€'(3) => 8 bytes
        bv.putUTF8(s);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(s);
        dos.flush();
        byte[] expected = baos.toByteArray();

        assertEquals(expected.length, bv.length);

        byte[] actual = new byte[bv.length];
        java.util.Arrays.fill(actual, (byte) 0);
        java.lang.reflect.Field f = ByteVector.class.getDeclaredField("data");
        f.setAccessible(true);
        byte[] internal = (byte[]) f.get(bv);
        java.util.Arrays.setAll(actual, i -> internal[i]);
        assertArrayEquals(expected, actual);
    }
}