import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
byte[] src = new byte[] { 9, 8, 7, 6 };
v.putByteArray(src, 1, 2);
assertEquals(7, v.data[1]);
assertEquals(2, v.length);
    }
}