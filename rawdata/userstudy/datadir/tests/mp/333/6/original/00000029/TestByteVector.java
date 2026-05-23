import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A" + '\u0000' + '\u007F' + '\u0080' + '\u07FF' + '\u0800' + "Z";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new DataOutputStream(bos).writeUTF(s);
        byte[] expected = bos.toByteArray();

        ByteVector v = new ByteVector(1);
        v.putUTF8(s);
        byte[] actual = java.util.Arrays.copyOf(v.data, v.length);

        assertArrayEquals(expected, actual);
        assertEquals(2 + s.getBytes(StandardCharsets.UTF_8).length, v.length);
    }
}