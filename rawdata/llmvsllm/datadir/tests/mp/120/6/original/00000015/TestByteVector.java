import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(8);
bv.putLong(0x0102030405060708L);
assertEquals((byte) 0x01, bv.data[0]);
assertEquals((byte) 0x04, bv.data[3]);
    }
}