import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3);

        bv.putUTF8("\u0800"); // requires 3-byte UTF8 encoding, triggers general path + enlarge
        bv.putByteArray(null, 0, 1); // should append one zero byte without NPE

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        assertArrayEquals(new byte[] {0, 3, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0}, java.util.Arrays.copyOf(data, 6));

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        assertEquals(6, lenF.getInt(bv));
    }
}