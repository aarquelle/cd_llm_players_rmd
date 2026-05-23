import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
        ByteVector bv2 = new ByteVector(2);
        assertNotEquals(bv.putInt(999).data.length, bv2.data.length);
    }
}