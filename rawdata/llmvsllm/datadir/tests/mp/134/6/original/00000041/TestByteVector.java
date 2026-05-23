import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4); // force enlarge path
        bv.putUTF8("A\u0080B"); // 0x80 encoded as 2 bytes in modified UTF-8; total 4 bytes payload

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        java.lang.reflect.Field lenField = ByteVector.class.getDeclaredField("length");
        lenField.setAccessible(true);
        int len = (Integer) lenField.get(bv);

        assertEquals(6, len); // 2 length bytes + 4 payload bytes
        assertArrayEquals(new byte[] {0, 4, 'A', (byte) 0xC2, (byte) 0x80, 'B'}, java.util.Arrays.copyOf(data, 6));
    }
}