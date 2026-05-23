import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(0);
        bv.putUTF8("Hello");
        assertEquals(5, bv.length);
        assertArrayEquals(new byte[]{0, -34}, bv.data);
    }
}