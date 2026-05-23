import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        
ByteVector bv = new ByteVector(2);
bv.put12(0x1, 0x234);
assertEquals(3, bv.length);
assertEquals(0x1, bv.data[0] & 0xFF);

    }
}