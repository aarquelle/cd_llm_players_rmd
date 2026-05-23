import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
bv.putUTF8("ABC");
assertArrayEquals(new byte[] { 'A', 'B', 'C' }, Arrays.copyOfRange(bv.data, 2, 5));
assertEquals(5, bv.length);
    }
}