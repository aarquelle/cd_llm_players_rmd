import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u00FF"); // Latin-1 'ÿ' (U+00FF)

        // Expected modified UTF-8 encoding length=2, bytes: C3 BF
        assertEquals((byte) 0xC3, bv.data[2]);
        assertEquals((byte) 0xBF, bv.data[3]);
    }
}