import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge in putUTF8 for this input
        bv.putUTF8("\u0000\u0080\u0800"); // 2-byte, 2-byte, 3-byte => total 7 bytes

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(bv);

        assertEquals(9, len); // 2 bytes length + 7 bytes payload
        assertArrayEquals(
                new byte[] {
                        0x00, 0x07,
                        (byte) 0xC0, (byte) 0x80,
                        (byte) 0xC2, (byte) 0x80,
                        (byte) 0xE0, (byte) 0xA0, (byte) 0x80
                },
                java.util.Arrays.copyOf(data, len)
        );
    }
}