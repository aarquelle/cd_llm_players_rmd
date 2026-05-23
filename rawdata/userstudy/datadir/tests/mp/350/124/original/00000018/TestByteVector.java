import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(1);
        vector.putShort(1);
        vector.putShort(10);        
            
        assertEquals(1, vector.data[0]);
        assertEquals(10, vector.data[1]);
    }
}