import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector();
        v.putUTF8("\u00A2"); // U+00A2 should encode as C2 A2 in modified UTF-8/UTF-8

        assertEquals(0xA2, v.data[3] & 0xFF);
    }
}