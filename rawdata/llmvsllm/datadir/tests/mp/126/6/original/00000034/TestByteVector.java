import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                copyRec(v, out, 0);
        return out;
            return;
        }
        out[i] = v.data[i];
        copyRec(v, out, i + 1);
        String s = "A\u0000\u07FF\u0800";
        ByteVector v = new ByteVector(1).putUTF8(s);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        new DataOutputStream(bos).writeUTF(s);
        byte[] expected = bos.toByteArray();

        assertArrayEquals(expected, copyPrefix(v));
    }
}