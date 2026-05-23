import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// 2-byte UTF8
bv.putUTF8("\u0080");
assertEquals(0, bv.data[0] & 0xFF);
assertEquals(2, bv.data[1] & 0xFF);
    }
}