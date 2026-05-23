import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector();
bv.putByte(0x12);
assertEquals(1, bv.length);
assertEquals(0x12, bv.data[0] & 0xFF);
    }
}