import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                f.setAccessible(true);
        return ((Integer) f.get(v)).intValue();
        f.setAccessible(true);
        return (byte[]) f.get(v);
        ByteVector v = new ByteVector(2);
        v.putShort(0x1234);

        assertEquals(2, length(v));
        assertArrayEquals(new byte[] { 0x12, 0x34 }, Arrays.copyOf(data(v), length(v)));
    }
}