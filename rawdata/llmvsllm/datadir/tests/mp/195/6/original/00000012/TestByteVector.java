import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(4);
bv.putInt(0x11223344);
assertEquals(0x11, bv.data[0] & 0xFF);
assertEquals(0x44, bv.data[3] & 0xFF);
    }
}