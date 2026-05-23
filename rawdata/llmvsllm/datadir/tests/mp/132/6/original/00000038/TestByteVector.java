import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("\u0000\u0080\u0800");

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        int length = (int) lengthField.get(bv);

        byte[] actual = Arrays.copyOf(data, length);

        byte[] expected = new byte[] {
            0x00, 0x07,                 // byte length = 7 (as computed by this implementation)
            (byte) 0xC0, (byte) 0x80,    // U+0000 (2 bytes)
            (byte) 0xC2, (byte) 0x80,    // U+0080 (2 bytes)
            (byte) 0xE0, (byte) 0xA0, (byte) 0x80 // U+0800 (3 bytes)
        };

        assertArrayEquals(expected, actual);
    }
}