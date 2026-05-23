import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putByte(0x11).putByte(0x22).putByte(0x33);
assertEquals((byte) 0x11, v.data[0]);
assertEquals((byte) 0x22, v.data[1]);
    }
}