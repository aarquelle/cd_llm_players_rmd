import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("\u0800"); // 1 char, encoded length should be 3 bytes in modified UTF-8

        assertEquals(5, bv.length); // 2 bytes length prefix + 3 bytes payload
        assertEquals(3, bv.data[1] & 0xFF); // low byte of length prefix must be 3
    }
}