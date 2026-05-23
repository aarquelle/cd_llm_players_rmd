import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(3);
int oldCap = v.data.length;
v.putUTF8("Hi");
assertTrue(v.data.length > oldCap);
assertEquals(4, v.length);
    }
}