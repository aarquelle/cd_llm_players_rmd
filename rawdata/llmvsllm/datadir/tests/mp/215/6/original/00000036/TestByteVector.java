import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u00A2\u20AC"); // UTF-8 bytes: 41 C2 A2 E2 82 AC, length=6

        assertEquals(8, bv.length);
        assertEquals(
                "000641c2a2e282ac",
                String.format("%02x%02x%02x%02x%02x%02x%02x%02x",
                        bv.data[0], bv.data[1], bv.data[2], bv.data[3],
                        bv.data[4], bv.data[5], bv.data[6], bv.data[7])
        );
    }
}