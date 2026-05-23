import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = new String(new char[128]).replace('\0', 'a');

        ByteVector bv = new ByteVector();
        bv.putUTF8(s);

        assertEquals(130, bv.length);
        assertEquals(128, ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF));
    }
}