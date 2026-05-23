import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putUTF8("A\u0800");
        bv.putByteArray(null, 0, 2);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertArrayEquals(new byte[] {0, 4, 0x41, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0, 0}, java.util.Arrays.copyOf(data, 8));
        assertEquals(8, bv.length);
    }
}