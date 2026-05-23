import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector v = new ByteVector(4);
        v.putInt(0x01020304); // length=4, capacity=4
        v.putByte(0x05);      // triggers enlarge with length1=8, length2=5 -> old: 8, new: 8 (no diff)

        // Force equality case: make length=4, capacity=4, then enlarge with size=4 => length1=8, length2=8
        ByteVector v2 = new ByteVector(4);
        v2.putInt(0x01020304); // length=4
        v2.putByteArray(new byte[] { 1, 2, 3, 4 }, 0, 4); // triggers enlarge with size=4, equality

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(v2);

        assertEquals(8, v2.length);
        assertEquals(8, data.length);
    }
}