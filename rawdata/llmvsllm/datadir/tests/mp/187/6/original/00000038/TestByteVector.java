import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x7F);
        bv.putUTF8("A\u0080\u0800"); // 'A' (1 byte), U+0080 (2 bytes), U+0800 (3 bytes) => utfLen=6

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        assertArrayEquals(
                new byte[] { (byte) 0x7F, 0x00, 0x06, 0x41, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                java.util.Arrays.copyOf(data, 9)
        );

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        assertEquals(9, ((Integer) lenF.get(bv)).intValue());
    }
}