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
        before[0] = 0x7F;
        before[1] = 0x7F;
        before[2] = 0x7F; // unused
        before[3] = 0x7F; // unused

        bv.putByte(1).putByte(2);
        bv.putInt(0x01020304); // triggers enlarge

        byte[] after = (byte[]) dataField.get(bv);
        assertEquals(0, after[2]);
        assertEquals(0x04, after[5]);
    }
}