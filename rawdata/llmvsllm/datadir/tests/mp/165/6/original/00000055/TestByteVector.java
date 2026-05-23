import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);
        bv.putByte(0x7A).putByte(0x55); // triggers enlarge(1)

        assertAll(
                () -> assertTrue(bv.data.length >= 2, "enlarge must ensure enough capacity for additional bytes"),
                () -> assertArrayEquals(new byte[] { (byte) 0x7A, (byte) 0x55 }, new byte[] { bv.data[0], bv.data[1] },
                        "enlarge must preserve existing content")
        );
    }
}