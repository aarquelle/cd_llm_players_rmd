import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
bv.putByte(1).putByte(2).putByte(3);
assertEquals(3, bv.length);
assertEquals(3, bv.data[2] & 0xFF);
    }
}