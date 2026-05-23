import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
v.putInt(0x12345678);
assertEquals((byte) 0x12, v.data[0]);
assertEquals((byte) 0x78, v.data[3]);
    }
}