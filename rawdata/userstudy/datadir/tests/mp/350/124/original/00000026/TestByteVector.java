import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(1);
        vector.put12(1,3);
        vector.put12(10,6);        
            
        assertEquals(6, vector.data.length);
       
    }
}