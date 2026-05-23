import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1);
        v.putUTF8("\u0080"); // non-ASCII, 2-byte UTF8 encoding

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int len = (Integer) lenF.get(v);

        assertEquals("len", 4, len); // 2 bytes length prefix + 2 bytes payload
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC2, (byte) 0x80}, new byte[] {data[0], data[1], data[2], data[3]});
    }
}