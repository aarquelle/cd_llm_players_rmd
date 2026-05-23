import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector();
    bv.put12(0x56, 0x789A);
    assertEquals(3, bv.length);
    }
}