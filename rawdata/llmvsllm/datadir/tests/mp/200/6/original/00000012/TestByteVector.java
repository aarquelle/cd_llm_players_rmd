import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.put11(0x7F, 0x80);
assertEquals((byte) 0x7F, bv.data[0]);
assertEquals((byte) 0x80, bv.data[1]);
    }
}