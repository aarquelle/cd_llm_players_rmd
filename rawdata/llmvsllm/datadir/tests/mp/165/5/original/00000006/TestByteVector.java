import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(1).putByte(2);      // length == data.length == 2
        v.putByteArray(null, 0, 1);   // enlarge(1) copies only 'length' bytes; then length becomes 3

        assertEquals(0, v.data[2]);
        assertEquals(3, v.length);
    }
}