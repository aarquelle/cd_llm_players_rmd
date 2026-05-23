import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector();
        v.putUTF8("\u0800"); // requires 3-byte UTF8 encoding, so byteLength=3
        assertEquals((byte) 0, v.data[0]);
        assertEquals((byte) 3, v.data[1]);
    }
}