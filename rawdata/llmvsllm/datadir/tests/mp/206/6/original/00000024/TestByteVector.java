import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // 26 chars -> 28 bytes total (2 length + 26)
String s = "abcdefghijklmnopqrstuvwxyz";
ByteVector bv = new ByteVector(10);
bv.putUTF8(s);
assertEquals(28, bv.length);
assertTrue(bv.data.length >= 28);
    }
}