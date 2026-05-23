import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                char[] chars = new char[300];
        java.util.Arrays.fill(chars, 'a');
        String s = new String(chars);

        ByteVector bv = new ByteVector();
        bv.putUTF8(s);

        assertEquals(300, ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF));
    }
}