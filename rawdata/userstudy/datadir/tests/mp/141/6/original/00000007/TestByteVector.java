import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector();
byte[] arr = { 1, 2, 3 };
bv.putByteArray(arr, 0, arr.length);
assertEquals(arr.length, bv.length);
assertEquals(1, bv.data[0]);
    }
}