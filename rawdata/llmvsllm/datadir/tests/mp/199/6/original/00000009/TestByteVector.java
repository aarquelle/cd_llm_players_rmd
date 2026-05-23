import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector v = new ByteVector(3);
v.put12(0xAB, 0xCDEF);
assertEquals((byte) 0xAB, v.data[0]);
assertEquals((byte) 0xCD, v.data[1]);
    }
}