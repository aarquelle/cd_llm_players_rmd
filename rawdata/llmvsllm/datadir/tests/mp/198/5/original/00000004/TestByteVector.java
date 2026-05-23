import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0);

        try {
            bv.putByte(2); // triggers enlarge needed, but diff makes enlarge(0) -> should fail
            assertEquals(2, bv.length);
        } catch (ArrayIndexOutOfBoundsException ex) {
            fail("putByte should enlarge when needed, not throw");
        }
    }
}