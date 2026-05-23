import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector byteV = new ByteVector(4);
        byteV.putByte(64);
        byteV.putByte(67);
        byteV.put11(6, 7);
        byteV.putShort(3);
        assertEquals(5, byteV.length);
    }
}