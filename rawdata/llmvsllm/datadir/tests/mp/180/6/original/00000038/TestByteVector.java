import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0000\u0800");

        assertEquals(7, bv.length);

        int b0 = bv.data[0] & 0xFF, b1 = bv.data[1] & 0xFF, b2 = bv.data[2] & 0xFF, b3 = bv.data[3] & 0xFF,
            b4 = bv.data[4] & 0xFF, b5 = bv.data[5] & 0xFF, b6 = bv.data[6] & 0xFF;

        assertEquals(
            "00 05 C0 80 E0 A0 80",
            String.format("%02X %02X %02X %02X %02X %02X %02X", b0, b1, b2, b3, b4, b5, b6)
        );
    }
}