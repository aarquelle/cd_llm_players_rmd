import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x11).putByte(0x22);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] before = (byte[]) dataField.get(bv);

        bv.putByteArray(new byte[]{0x33}, 0, 1);

        byte[] after = (byte[]) dataField.get(bv);

        assertNotSame(before, after);
        assertArrayEquals(new byte[]{0x11, 0x22, 0x33}, new byte[]{after[0], after[1], after[2]});
    }
}