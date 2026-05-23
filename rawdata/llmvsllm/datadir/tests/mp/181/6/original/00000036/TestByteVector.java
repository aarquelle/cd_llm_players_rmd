import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putUTF8("A\u07FF\u0800");

        assertEquals(8, bv.length);

        int prefix = ((bv.data[0] & 0xFF) << 8) | (bv.data[1] & 0xFF);
        int sum =
                prefix +
                (bv.data[2] & 0xFF) +
                (bv.data[3] & 0xFF) +
                (bv.data[4] & 0xFF) +
                (bv.data[5] & 0xFF) +
                (bv.data[6] & 0xFF) +
                (bv.data[7] & 0xFF);

        assertEquals(1092, sum);
    }
}