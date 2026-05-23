import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            String s = "héllo";
    ByteVector bv = new ByteVector();
    bv.putUTF8(s);
    assertTrue(bv.length > 2);
    assertEquals((byte) 0, bv.data[0]);
    }
}