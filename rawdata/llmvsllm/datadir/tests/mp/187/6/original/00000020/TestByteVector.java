import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(16);
// A (1) + cent (2) + B (1) => 4 bytes, length field should be 0x0004
v.putUTF8("A\u00A2B");
assertEquals(6, v.length);
assertArrayEquals(new byte[] { 0x00, 0x04 }, new byte[] { v.data[0], v.data[1] });
    }
}