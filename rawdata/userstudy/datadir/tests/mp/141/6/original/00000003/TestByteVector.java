import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector();
    bv.put11(0x12, 0x34);
    assertEquals(2, bv.length);
    assertEquals((byte) 0x12, bv.data[0]);
    }
}