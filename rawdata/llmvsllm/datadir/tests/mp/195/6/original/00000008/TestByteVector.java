import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putShort(0x1234);
assertEquals(0x12, bv.data[0] & 0xFF);
assertEquals(0x34, bv.data[1] & 0xFF);
    }
}