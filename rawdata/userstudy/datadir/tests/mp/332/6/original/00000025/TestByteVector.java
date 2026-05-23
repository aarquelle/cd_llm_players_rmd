import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putShort(0xABCD);

        assertAll(
                () -> assertArrayEquals(new byte[] { (byte) 0xAB, (byte) 0xCD }, v.data),
                () -> assertEquals(2, v.length)
        );
    }
}