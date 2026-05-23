import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(4);
byte[] b = new byte[] { 9, 8, 7, 6, 5 };
v.putByteArray(b, 1, 3);
assertEquals(3, v.length);
assertEquals(6, v.data[2]);
    }
}