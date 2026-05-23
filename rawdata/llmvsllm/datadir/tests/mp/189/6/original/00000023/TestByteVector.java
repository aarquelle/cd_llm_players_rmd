import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putByte(1).putByte(2);
// triggers enlarge with size=1; expect doubled to 4 (since max(4, 3)=4)
bv.putByte(3);
assertEquals(4, bv.data.length);
assertEquals(3, bv.length);
    }
}