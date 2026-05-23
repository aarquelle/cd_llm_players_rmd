import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector();
int initLength = bv.length;
bv.put12(0x07, 0x1234);
assertEquals(initLength + 3, bv.length);
    }
}