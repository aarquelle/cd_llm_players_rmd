import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(10);
        bv.putUTF8("é"); // requires 2 bytes in modified UTF8

        assertEquals(4, bv.length); // 2 bytes length header + 2 bytes data
    }
}