import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.put11(0x01, 0xFF);
assertEquals(2, v.length);
assertArrayEquals(new byte[] { 1, (byte) 0xFF }, new byte[] { v.data[0], v.data[1] });
    }
}