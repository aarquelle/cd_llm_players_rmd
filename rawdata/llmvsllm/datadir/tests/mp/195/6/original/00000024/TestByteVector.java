import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// E0 A0 80
bv.putUTF8("\u0800");
assertEquals(0x80, bv.data[4] & 0xFF);
assertEquals(5, bv.length);
    }
}