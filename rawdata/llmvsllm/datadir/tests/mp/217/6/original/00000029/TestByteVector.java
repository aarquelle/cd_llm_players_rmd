import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        ByteVector ret = v.put11(0xAB, 0xCD);

        assertSame(v, ret);
        assertEquals("2:[-85,-51]", v.length + ":[" + v.data[0] + "," + v.data[1] + "]");
    }
}