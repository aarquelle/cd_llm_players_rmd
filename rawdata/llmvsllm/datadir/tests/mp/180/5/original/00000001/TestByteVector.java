import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.data[0] = (byte) 0x12;
        v.data[1] = (byte) 0x34;
        v.length = 1;

        v.putByte(0x56); // fills capacity
        v.putByte(0x78); // triggers enlarge

        assertEquals((byte) 0x00, v.data[2]);
        assertEquals((byte) 0x78, v.data[2]);
    }
}