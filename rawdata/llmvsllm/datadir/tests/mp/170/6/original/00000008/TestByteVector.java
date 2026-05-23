import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector();
        v.putByte(0x12);

        Field f = ByteVector.class.getDeclaredField("length");
        f.setAccessible(true);
        assertEquals(1, ((Integer) f.get(v)).intValue());
    }
}