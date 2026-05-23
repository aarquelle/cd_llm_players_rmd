import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(1);
        assertEquals(1,vector.data.length);
        vector.putByte(1);
        vector.putByte(10);        
            
        assertEquals(2,vector.data.length);
    }
}