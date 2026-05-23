import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
v.putUTF8("\u0800");
assertEquals(5, v.length);
assertArrayEquals(new byte[] { 0x00, 0x03, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, new byte[] { v.data[0], v.data[1], v.data[2], v.data[3], v.data[4] });
    }
}