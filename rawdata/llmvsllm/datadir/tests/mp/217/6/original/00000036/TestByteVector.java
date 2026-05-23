import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x7F);

        ByteVector returned = v.put11(0x80, 0xFF);

        assertSame(v, returned);
        assertEquals(0x7F80FF, ((v.data[0] & 0xFF) << 16) | ((v.data[1] & 0xFF) << 8) | (v.data[2] & 0xFF));
    }
}