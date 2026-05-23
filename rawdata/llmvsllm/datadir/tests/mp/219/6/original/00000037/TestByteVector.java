import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7F).putUTF8("A\u00E9\u0800");

        assertEquals(9, bv.length);
        assertEquals(0x06, bv.data[2] & 0xFF);
    }
}