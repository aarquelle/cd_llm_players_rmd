import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0000\u0080\u0800");

        assertEquals(2 + 2 + 3 + 3, bv.length);

        int header = ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF);
        assertEquals(2 + 3 + 3, header);
    }
}