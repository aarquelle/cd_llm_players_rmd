import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(0);
        assertArrayEquals(new byte[0], bv.data);
        bv.putByte(127);
        assertEquals(new byte[]{127}, bv.data);
    }
}