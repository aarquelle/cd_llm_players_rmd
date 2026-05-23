import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8("é"); // U+00E9 should encode as C3 A9 in UTF-8

        java.lang.reflect.Field dataF = ByteVector.class.getDeclaredField("data");
        java.lang.reflect.Field lenF = ByteVector.class.getDeclaredField("length");
        dataF.setAccessible(true);
        lenF.setAccessible(true);

        byte[] data = (byte[]) dataF.get(bv);
        int length = (Integer) lenF.get(bv);

        assertEquals(4, length);
        assertArrayEquals(new byte[] {0, 2, (byte) 0xC3, (byte) 0xA9},
                new byte[] {data[0], data[1], data[2], data[3]});
    }
}