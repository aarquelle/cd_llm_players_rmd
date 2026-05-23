import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(3);
v.put12(0xAA, 0x1234);
assertEquals((byte) 0xAA, v.data[0]);
assertEquals((byte) 0x34, v.data[2]);
    }
}