import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putByte(0x01).putShort(0x0203);
assertEquals((byte) 0x01, v.data[0]);
assertEquals((byte) 0x03, v.data[2]);
    }
}