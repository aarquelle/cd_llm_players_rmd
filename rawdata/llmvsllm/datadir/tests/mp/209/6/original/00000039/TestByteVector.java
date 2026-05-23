import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x55).putUTF8("\u0080\u0800").putByte(0x66);

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int length = (Integer) lenF.get(bv);

        assertEquals(1 + 2 + 5 + 1, length);
        assertArrayEquals(
                new byte[] {
                        0x55,
                        0x00, 0x05,              // UTF8 byte length
                        (byte) 0xC2, (byte) 0x80, // U+0080
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80, // U+0800
                        0x66
                },
                java.util.Arrays.copyOf(data, length)
        );
    }
}