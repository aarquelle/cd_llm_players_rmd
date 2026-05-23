import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector(4);
    bv.putInt(0x01020304);
    assertEquals(4, bv.length);
    assertEquals(0x01, bv.data[0] & 0xFF);
    }
}