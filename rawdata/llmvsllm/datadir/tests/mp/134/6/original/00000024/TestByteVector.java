import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x42);

        String s = "A\u0000\u0800"; // 1 + 2 + 3 bytes = 6
        bv.putUTF8(s);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int len = (int) lengthField.get(bv);

        assertArrayEquals(new byte[] {
                0x42,
                0x00, 0x06,
                0x41,
                (byte) 0xC0, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80
        }, Arrays.copyOf(data, len));

        assertEquals(9, len);
    }
}