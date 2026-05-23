import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(0);
        vector = vector.putLong(30000000000000);
        assertEquals(30000000000000, vector.data[7] + vector.data[6] + vector.data[5] + vector.data[4] + vector.data[3] + vector.data[2] + vector.data[1] + vector.data[0]);
        
    }
}