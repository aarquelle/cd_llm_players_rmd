import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putByte(0x11).putByte(0x22);
        v.putByteArray(null, 0, 3); // forces enlarge with size=3 when length==2 and capacity==2

        assertEquals(5, v.data.length);
        assertArrayEquals(new byte[] { 0x11, 0x22 }, new byte[] { v.data[0], v.data[1] });
    }
}