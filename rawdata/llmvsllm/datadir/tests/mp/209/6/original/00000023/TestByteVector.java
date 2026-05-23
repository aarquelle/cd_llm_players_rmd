import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
byte[] src = new byte[] { 10, 20, 30, 40, 50 };
v.putByteArray(src, 1, 3);
assertEquals(20, v.data[0] & 0xFF);
assertEquals(40, v.data[2] & 0xFF);
    }
}