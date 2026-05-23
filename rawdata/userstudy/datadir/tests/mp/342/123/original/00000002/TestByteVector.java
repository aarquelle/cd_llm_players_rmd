import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector byteVector = new ByteVector();
        ByteVector newByteVector = byteVector.putUFT8('\001');
        assertTrue(newByteVector.data-contains('\001'));
    }
}