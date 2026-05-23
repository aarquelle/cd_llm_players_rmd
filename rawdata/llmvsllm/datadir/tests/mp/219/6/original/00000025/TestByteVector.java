import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(12);
        bv.putUTF8("A\u0080\u0800B");

        assertEquals(
                "len=" + bv.length +
                        " b0=" + (bv.data[0] & 0xFF) +
                        " b1=" + (bv.data[1] & 0xFF) +
                        " b2=" + (bv.data[2] & 0xFF) +
                        " b3=" + (bv.data[3] & 0xFF) +
                        " b4=" + (bv.data[4] & 0xFF) +
                        " b5=" + (bv.data[5] & 0xFF) +
                        " b6=" + (bv.data[6] & 0xFF) +
                        " b7=" + (bv.data[7] & 0xFF) +
                        " b8=" + (bv.data[8] & 0xFF),
                9,
                bv.length
        );
    }
}