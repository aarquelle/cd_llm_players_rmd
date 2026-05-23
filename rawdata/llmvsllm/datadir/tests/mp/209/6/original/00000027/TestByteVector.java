import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
v.putByte(0x7A);
v.putByteArray(new byte[] { 1, 2, 3, 4, 5 }, 0, 5);
assertTrue(v.data.length >= 6);
assertEquals(0x7A, v.data[0] & 0xFF);
    }
}