import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(4);
        bv.putByte(1).putByte(2).putByte(3);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] before = (byte[]) dataField.get(bv);
        before[3] = 99; // unused byte within current capacity

        bv.putByte(4); // fills capacity
        bv.putByte(5); // triggers enlarge and copy

        byte[] after = (byte[]) dataField.get(bv);
        assertEquals(0, after[3]);
        assertEquals(5, after[4]);
    }
}