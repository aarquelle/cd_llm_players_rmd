import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector b = new ByteVector(10000);
        ByteVector t = b.putByte(10000);
        byte[] r = b.data;
        assertEquals(10000, r.length);
    }
}