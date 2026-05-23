import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(2);
        bv.putByte(0x11).putByte(0x22);

        Method enlarge = ByteVector.class.getDeclaredMethod("enlarge", int.class);
        enlarge.setAccessible(true);
        enlarge.invoke(bv, 3);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertAll(
                () -> assertTrue(data.length >= 5, "capacity must be at least length+size"),
                () -> assertArrayEquals(new byte[] { 0x11, 0x22 }, new byte[] { data[0], data[1] }, "prefix must be preserved")
        );
    }
}