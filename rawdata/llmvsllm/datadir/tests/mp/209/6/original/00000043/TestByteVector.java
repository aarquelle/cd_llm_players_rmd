import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);
        bv.putByte(0x55).putUTF8("A\u0800");

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = lenF.getInt(bv);

        assertEquals(8, len);
        assertArrayEquals(new byte[] {
                (byte) 0x55,
                0x00, 0x04,
                0x41,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, Arrays.copyOf(data, len));
    }
}