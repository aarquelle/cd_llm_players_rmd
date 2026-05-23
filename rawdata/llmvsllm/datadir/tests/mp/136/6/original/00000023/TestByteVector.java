import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
byte[] src = new byte[] { 1, 2, 3, 4 };
v.putByteArray(src, 1, 2);
assertEquals((byte) 3, v.data[1]);
assertTrue(v.data.length >= 2);
    }
}