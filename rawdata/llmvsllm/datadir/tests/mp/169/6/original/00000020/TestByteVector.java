import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(2);
v.putUTF8("hello");
assertTrue(v.data.length >= 7);
assertEquals("hello", new String(v.data, 2, 5, StandardCharsets.UTF_8));
    }
}