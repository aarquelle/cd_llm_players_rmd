import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(5);
v.putUTF8("ABC");
assertEquals((byte) 'A', v.data[2]);
assertEquals(5, v.length);
    }
}