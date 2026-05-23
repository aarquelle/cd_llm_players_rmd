import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(1).putByte(0xAB).putByte(0xCD);

        Field dataF = ByteVector.class.getDeclaredField("data");
        dataF.setAccessible(true);
        byte[] data = (byte[]) dataF.get(v);

        assertEquals(2, v.length);
        assertArrayEquals(new byte[]{(byte) 0xAB, (byte) 0xCD}, new byte[]{data[0], data[1]});
    }
}