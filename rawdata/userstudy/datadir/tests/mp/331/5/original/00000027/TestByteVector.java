import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("A");
        assertEquals(1, ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF));
        assertEquals((byte) 'A', bv.data[2]);
    }
}