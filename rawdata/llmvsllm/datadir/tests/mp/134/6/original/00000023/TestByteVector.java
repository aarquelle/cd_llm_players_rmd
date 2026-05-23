import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(0x42);

        String s = "A\u0000\u0800"; // 'A' (1 byte), NUL (2 bytes), U+0800 (3 bytes) => 6 bytes
        bv.putUTF8(s);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int len = (int) lengthField.get(bv);

        assertArrayEquals(new byte[] {
                0x42,
                0x00, 0x06,                 // modified UTF8 byte length
                0x41,                       // 'A'
                (byte) 0xC0, (byte) 0x80,   // NUL
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        }, java.util.Arrays.copyOf(data, len));

        assertEquals(1 + 2 + 6, len);
    }
}