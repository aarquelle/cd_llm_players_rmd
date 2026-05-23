import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putShort(0x1234);
assertEquals(0x12, v.data[0] & 0xFF);
assertEquals(0x34, v.data[1] & 0xFF);
    }
}