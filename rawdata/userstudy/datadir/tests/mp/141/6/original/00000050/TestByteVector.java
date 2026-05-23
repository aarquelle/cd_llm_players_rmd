import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector();
int initLength = bv.length;
bv.putLong(0x0123456789ABCDEFL);
assertEquals(initLength + 8, bv.length);
    }
}