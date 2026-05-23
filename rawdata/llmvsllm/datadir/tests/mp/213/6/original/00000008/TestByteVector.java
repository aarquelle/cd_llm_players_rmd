import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(8);
v.putByte(0x7F).putLong(0x0102030405060708L);
assertTrue(v.data.length >= 9);
assertEquals((byte) 0x7F, v.data[0]);
    }
}