import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge on putInt

        bv.putInt(0x89ABCDEF);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertEquals(4, bv.length);
        assertArrayEquals(new byte[] { (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF },
                new byte[] { data[0], data[1], data[2], data[3] });
    }
}