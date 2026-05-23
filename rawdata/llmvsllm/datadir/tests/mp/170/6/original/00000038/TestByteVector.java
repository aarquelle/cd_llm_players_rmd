import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(8);
v.putUTF8("Hi");
assertEquals(4, length(v));
assertArrayEquals(new byte[] { 0, 2, 'H', 'i' }, Arrays.copyOf(data(v), length(v)));
    }
}