import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putUTF8("\u07FF\u0800");

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(bv);
        int len = (Integer) lenF.get(bv);

        byte[] actual = Arrays.copyOfRange(data, 0, len);
        byte[] expected = new byte[] { 0x00, 0x05, (byte) 0xDF, (byte) 0xBF, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 };

        assertArrayEquals(expected, actual);
        assertEquals(7, len);
    }
}