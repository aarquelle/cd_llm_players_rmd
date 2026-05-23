import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                f.setAccessible(true);
        return (byte[]) f.get(v);
        ByteVector v = new ByteVector(1);
        v.putByte(1).putByte(2);

        assertTrue(data(v).length >= 2);
        assertArrayEquals(new byte[] { 1, 2 }, new byte[] { data(v)[0], data(v)[1] });
    }
}