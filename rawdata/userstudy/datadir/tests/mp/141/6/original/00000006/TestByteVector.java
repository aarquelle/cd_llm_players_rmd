import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector();
String s = "ñ";
bv.putUTF8(s);
assertTrue(bv.length >= 3);
assertEquals(2, (bv.data[0] << 8) | (bv.data[1] & 0xFF));
    }
}