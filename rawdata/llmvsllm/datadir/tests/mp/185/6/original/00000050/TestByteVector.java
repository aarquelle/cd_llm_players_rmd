import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(2);
        v.putUTF8("A\u20ACB"); // 'A'(1) + '€'(3) + 'B'(1) => 5 bytes payload, header=0x0005

        assertArrayEquals(new byte[] { 0, 5, 65, (byte) 0xE2, (byte) 0x82, (byte) 0xAC, 66 }, v.data);
        assertEquals(7, v.length);
    }
}