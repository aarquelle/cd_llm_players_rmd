import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
v.putByte(0x7F).putByteArray(null, 0, 2);
assertEquals(3, length(v));
assertArrayEquals(new byte[] { 0x7F, 0, 0 }, Arrays.copyOf(data(v), length(v)));
    }
}