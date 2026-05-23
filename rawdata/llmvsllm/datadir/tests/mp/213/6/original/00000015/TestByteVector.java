import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putByte(7);
v.putByteArray(new byte[] { 1, 2, 3 }, 0, 3);
assertTrue(v.data.length >= 4);
assertEquals(7, v.data[0]);
    }
}