import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            String s = "hello";
    ByteVector bv = new ByteVector();
    bv.putUTF8(s);
    assertEquals(s.length() + 2, bv.length);
    assertEquals((byte) s.length(), bv.data[0]);
    }
}