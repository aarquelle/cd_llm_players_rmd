import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(3);
        v.putUTF8("\u0080\u0800"); // requires 2-byte + 3-byte encoding => byteLength=5, total=7

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        assertArrayEquals(new byte[] { 0, 5, (byte) 0xC2, (byte) 0x80, (byte) 0xE0, (byte) 0xA0, (byte) 0x80 },
                new byte[] { data[0], data[1], data[2], data[3], data[4], data[5], data[6] });
        assertEquals(7, v.length);
    }
}