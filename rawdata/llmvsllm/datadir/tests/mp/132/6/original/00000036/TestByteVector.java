import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0000\u0080\u0800");

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        java.lang.reflect.Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int length = (int) lengthField.get(bv);

        byte[] expected = new byte[] {
            0x00, 0x08,             // modified UTF-8 byte length = 8
            (byte) 0xC0, (byte) 0x80, // U+0000
            (byte) 0xC2, (byte) 0x80, // U+0080
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800
        };

        assertArrayEquals(expected, java.util.Arrays.copyOf(data, length));
    }
}