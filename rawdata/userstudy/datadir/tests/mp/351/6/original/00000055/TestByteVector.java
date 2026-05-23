import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(10);
        bv.putUTF8("\u0800");

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        Field lengthF = ByteVector.class.getDeclaredField("length");
        lengthF.setAccessible(true);
        int length = ((Integer) lengthF.get(bv)).intValue();

        assertArrayEquals(new byte[] { 0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 }, Arrays.copyOf(data, 5));
        assertEquals(5, length);
    }
}