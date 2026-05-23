import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                String s = "A\u0080\u0800\u0001";
        ByteVector bv = new ByteVector(1);
        bv.putUTF8(s);

        byte[] expected = new byte[] {
                0x00, 0x07,
                0x41,
                (byte) 0xC2, (byte) 0x80,
                (byte) 0xE0, (byte) 0xA0, (byte) 0x80,
                0x01
        };

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        byte[] actual = Arrays.copyOf(data, bv.length);

        assertEquals(expected.length, bv.length);
        assertArrayEquals(expected, actual);
    }
}