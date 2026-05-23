import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(64);
        assertEquals(64,vector.data.length);
        for (int i=0, i<66, i++){
            vector.putByte(i);
            }
        assertEquals(66,vector.data.length);
    }
}