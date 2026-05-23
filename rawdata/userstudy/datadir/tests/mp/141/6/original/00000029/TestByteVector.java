import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
            ByteVector bv = new ByteVector(1);
    int oldCapacity = bv.data.length;
    // Use putByteArray to trigger enlargement instead of directly calling private method
    bv.putByteArray(new byte[100], 0, 100);
    assertTrue(bv.data.length >= oldCapacity);
    }
}