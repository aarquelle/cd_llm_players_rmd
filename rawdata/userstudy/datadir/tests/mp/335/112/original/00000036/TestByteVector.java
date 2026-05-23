import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(2);
        vector = vector.putByteArray(new byte[] {(byte)0, (byte)1}, 0, 2);
        assertEquals(2, vector.data.length);
        
    }
}