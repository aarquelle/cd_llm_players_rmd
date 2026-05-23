import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(20);
        bv.putLong(0x0102030405060708L);

        assertEquals(8, bv.length);
        assertEquals(0x08, bv.data[7] & 0xFF);
    }
}