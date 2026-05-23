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
        ByteVector v = new ByteVector(4);
        v.putByteArray(new byte[]{9, 8, 7, 6}, 1, 2);

        assertEquals(2, length(v));
        assertArrayEquals(new byte[]{8, 7}, Arrays.copyOf(data(v), length(v)));
    }
}