import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);

        ByteVector returned = bv.put11(0x80, 0x7F);

        assertSame(bv, returned);
        assertArrayEquals(new byte[] {(byte) 0x80, (byte) 0x7F}, java.util.Arrays.copyOf(bv.data, bv.length));
    }
}