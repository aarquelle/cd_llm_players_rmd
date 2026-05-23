import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putByteArray(null, 0, 3);
assertTrue(v.data.length >= 3);
assertEquals((byte) 0, v.data[2]);
    }
}