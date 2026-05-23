import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putByteArray(new byte[] { 1, 2, 3 }, 0, 3);
assertEquals(3, v.length);
assertTrue(v.data.length >= 3);
    }
}