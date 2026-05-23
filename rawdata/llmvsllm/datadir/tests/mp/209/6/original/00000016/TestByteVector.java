import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(10);
v.putUTF8("ABC");
assertEquals(0, v.data[0] & 0xFF);
assertEquals(3, v.data[1] & 0xFF);
    }
}