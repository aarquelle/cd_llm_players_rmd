import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
        bv.putByte(10);
        bv.putByte(20);
        
        assertEquals(10, bv.data[0]);
        assertEquals(20, bv.data[1]);
      
    }
}