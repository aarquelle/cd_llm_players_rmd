import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putByte(0x01).putByte(0xFF);

        assertArrayEquals(new byte[] { 0x01, (byte) 0xFF }, java.util.Arrays.copyOf(v.data, v.length));
    }
}