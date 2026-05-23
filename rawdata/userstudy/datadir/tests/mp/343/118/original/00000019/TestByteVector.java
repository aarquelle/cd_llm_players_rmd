import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(32);
        ByteVector bv2 = new ByteVector(32);
        assertEquals(bv.put11(2,2).data, bv2.putShort(2).putShort(2).data);
    }
}