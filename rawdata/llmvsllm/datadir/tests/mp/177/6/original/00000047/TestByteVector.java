import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
String s = "A\u00A9\u20AC";
byte[] expected = s.getBytes(StandardCharsets.UTF_8);
v.putUTF8(s);
byte[] actual = Arrays.copyOfRange(v.data, 2, v.length);
assertEquals(expected.length + 2, v.length);
assertArrayEquals(expected, actual);
    }
}