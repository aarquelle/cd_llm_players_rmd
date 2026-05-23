import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
v.putByte(0x11);
v.putInt(0x22334455);
assertEquals((byte) 0x11, v.data[0]);
assertEquals(5, v.length);
    }
}