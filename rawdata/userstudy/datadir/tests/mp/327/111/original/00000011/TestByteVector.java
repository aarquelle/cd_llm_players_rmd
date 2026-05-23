import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // test here!
        ByteVector bv = new ByteVector(1);
        bv.putByte(1);
        assertEquals(1, bv.data.length);
    }
}