import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // test here!
        ByteVetor bv = new ByteVector(1);
        bv.putByte(1);
        assertEquals(2, bv.length);
    }
}