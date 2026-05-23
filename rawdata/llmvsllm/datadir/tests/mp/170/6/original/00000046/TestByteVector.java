import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                f.setAccessible(true);
        return (Integer) f.get(v);
        f.setAccessible(true);
        return (byte[]) f.get(v);
        ByteVector v = new ByteVector(16);

        v.putUTF8("\u20AC");

        assertEquals(5, length(v));
        byte[] d = data(v);
        assertArrayEquals(new byte[] { 0, 3, (byte) 0xE2, (byte) 0x82, (byte) 0xAC },
                new byte[] { d[0], d[1], d[2], d[3], d[4] });
    }
}