import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {

        ByteVector bytevec = new ByteVector();
        assertEquals(bytevec, bytevec.putInt(0));

    }
}