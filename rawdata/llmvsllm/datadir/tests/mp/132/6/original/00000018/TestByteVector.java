import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putByteArray(new byte[] { 1, 2, 3, 4 }, 0, 4);
assertEquals(4, v.length);
assertTrue(v.data.length >= 4);
    }
}