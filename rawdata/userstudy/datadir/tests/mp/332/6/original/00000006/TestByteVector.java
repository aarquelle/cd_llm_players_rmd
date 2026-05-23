import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
v.put11(0x7E, 0x7F);
assertEquals(2, v.length);
assertArrayEquals(new byte[] { 0x7E, 0x7F }, new byte[] { v.data[0], v.data[1] });
    }
}