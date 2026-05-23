import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(1);
v.putByte(0x01);
v.putByte(0x02);
assertTrue(v.data.length >= 2);
assertEquals(0x01, v.data[0] & 0xFF);
    }
}