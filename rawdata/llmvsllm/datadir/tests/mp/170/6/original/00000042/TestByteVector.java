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
        ByteVector v = new ByteVector(8);
        v.putUTF8("Hi");

        int len = getLength(v);
        assertEquals(4, len);
        assertArrayEquals(new byte[] { 0, 2, (byte) 'H', (byte) 'i' }, java.util.Arrays.copyOf(getData(v), len));
    }
}