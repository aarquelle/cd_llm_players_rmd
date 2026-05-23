import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(7);
int oldCap = v.data.length;
v.putLong(0x0000000000000001L);
assertTrue(v.data.length > oldCap);
assertEquals(8, v.length);
    }
}