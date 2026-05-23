import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(3);
        vector = vector.put12(3, 4);
        assertEquals(3, vector.data[0]);
        assertEquals(3, vector.length);
    }
}