import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// 41 C2 80 42
bv.putUTF8("A\u0080B");
assertEquals(0x41, bv.data[2] & 0xFF);
assertEquals(0x42, bv.data[5] & 0xFF);
    }
}