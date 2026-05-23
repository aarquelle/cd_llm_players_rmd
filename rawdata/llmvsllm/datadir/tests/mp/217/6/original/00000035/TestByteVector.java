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
        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0x80, (byte) 0xFF }, v.data);
    }
}