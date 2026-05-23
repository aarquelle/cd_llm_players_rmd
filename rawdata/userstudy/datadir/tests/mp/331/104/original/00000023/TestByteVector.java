import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector b = new ByteVector();
        ByteVector bb = b.put12(1,2);
        assertEquals(1, bb.data[0]);
        assertEquals(2, bb.length);
    }
}