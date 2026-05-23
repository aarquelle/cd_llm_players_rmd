import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
bv.putUTF8("\u0800");
assertEquals((byte) 0xE0, bv.data[2]);
assertEquals(5, bv.length);
    }
}