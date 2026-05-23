import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector vector = new ByteVector(1);
        vector.putShort12(1);
        vector.putShort12(10);        
            
        assertEquals(6, vector.data.length);
       
    }
}