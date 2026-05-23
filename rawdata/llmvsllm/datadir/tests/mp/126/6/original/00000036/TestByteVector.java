import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0001\u007F\u0080\u07FF\u0800\u20AC\u0000Z";
        ByteVector v = new ByteVector(3);
        v.putUTF8(s);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new DataOutputStream(bos).writeUTF(s);
        byte[] expected = bos.toByteArray();

        assertEquals(expected.length, v.length);
        assertEquals(new String(expected, 2, expected.length - 2, StandardCharsets.UTF_8),
                new String(v.data, 2, v.length - 2, StandardCharsets.UTF_8));
    }
}