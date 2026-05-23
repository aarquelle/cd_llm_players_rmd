import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        byte[] b = { 1, 2, 3, 4 };
ByteVector bv = new ByteVector();
bv.putByteArray(b, 0, b.length);
assertEquals(b.length, bv.length);
assertEquals(1, bv.data[0] & 0xFF);
    }
}