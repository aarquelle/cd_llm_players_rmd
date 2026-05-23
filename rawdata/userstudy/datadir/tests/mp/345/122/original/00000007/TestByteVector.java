import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {

      ByteVector bytevec = new ByteVector();
        byte[] b = new byte[] { 1, 2, 3 };

        assertEquals(bytevec, bytevec.putByteArray(b, 0, 3));


    }
}