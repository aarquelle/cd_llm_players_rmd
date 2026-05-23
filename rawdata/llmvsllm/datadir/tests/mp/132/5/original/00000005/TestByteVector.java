import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector();
        v.putUTF8("A");
        assertArrayEquals(new byte[] {0, 1, 65}, java.util.Arrays.copyOf(v.data, v.length));
    }
}