import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(10);
        v.putUTF8("A\u00A9");

        assertEquals(5, v.length);
        assertEquals((byte) 0xA9, v.data[4]);
    }
}