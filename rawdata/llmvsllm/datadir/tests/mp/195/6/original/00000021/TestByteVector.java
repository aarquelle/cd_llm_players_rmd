import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// C2 80
bv.putUTF8("\u0080");
assertEquals(0xC2, bv.data[2] & 0xFF);
assertEquals(0x80, bv.data[3] & 0xFF);
    }
}