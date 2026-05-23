import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u00A3"); // U+00A3 '£' => UTF-8: C2 A3
        assertEquals((byte) 0xA3, bv.data[3]);
    }
}