import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.put12(0x7F, 0xABCD);

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        Field lengthF = ByteVector.class.getDeclaredField("length");
        lengthF.setAccessible(true);
        int length = (Integer) lengthF.get(bv);

        assertArrayEquals(new byte[] { (byte) 0x7F, (byte) 0xAB, (byte) 0xCD },
                new byte[] { data[0], data[1], data[2] });
        assertEquals(3, length);
    }
}