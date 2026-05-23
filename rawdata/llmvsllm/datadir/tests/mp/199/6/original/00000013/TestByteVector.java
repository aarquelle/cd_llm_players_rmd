import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(8);
v.putLong(0x0102030405060708L);
assertEquals((byte) 0x01, v.data[0]);
assertEquals((byte) 0x08, v.data[7]);
    }
}