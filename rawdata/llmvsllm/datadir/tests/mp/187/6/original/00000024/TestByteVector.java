import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putByte(0x11).putByte(0x22);
v.putByte(0x33);
assertArrayEquals(new byte[] { 0x11, 0x22, 0x33 }, new byte[] { v.data[0], v.data[1], v.data[2] });
assertEquals(3, v.length);
    }
}