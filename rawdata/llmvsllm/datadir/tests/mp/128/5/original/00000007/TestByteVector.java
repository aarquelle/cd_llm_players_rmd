import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);

        byte[] before = (byte[]) dataField.get(bv);
        before[1] = 0x7F; // will be overwritten by second putByte
        before[2] = 0x7F; // unused
        before[3] = 0x7F; // unused

        bv.putByte(0).putByte(0);
        bv.putInt(0); // triggers enlarge; new bytes written are all zeros

        byte[] after = (byte[]) dataField.get(bv);
        assertEquals(0, after[2]); // should be zero if unused bytes weren't copied
        assertSame(before, after); // should not have enlarged if copy was wrong
    }
}