import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putUTF8("a\u0800b");

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(bv);

        Field lenF = ByteVector.class.getDeclaredField("length");
        lenF.setAccessible(true);
        int length = (Integer) lenF.get(bv);

        byte[] actual = Arrays.copyOf(data, length);
        byte[] expected = new byte[] { 0, 5, 0x61, (byte) 0xE0, (byte) 0xA0, (byte) 0x80, 0x62 };

        assertArrayEquals(expected, actual);
    }
}