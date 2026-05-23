import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector byteV = new ByteVector(1);
        byteV.put12(6, 7);
        byteV.putByte(67);
        assertEquals(null, byteV.data[10]);
    }
}