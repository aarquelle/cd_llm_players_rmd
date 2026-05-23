import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putByteArray(new byte[] { 1, 2 }, 0, 2);
bv.putByteArray(null, 0, 5);
assertEquals(7, bv.data.length);
assertEquals(7, bv.length);
    }
}