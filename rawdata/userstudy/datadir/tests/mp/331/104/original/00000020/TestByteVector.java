import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector b = new ByteVector();
        ByteVector bb = b.putUTF8("Diddy");
        assertEquals(0, bb.data[0]);
        assertEquals(7, bb.length);
    }
}