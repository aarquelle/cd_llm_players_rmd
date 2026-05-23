import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge path for +4 bytes
        int i = 0x80FF0102;

        ByteVector returned = bv.putInt(i);

        var lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        var dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);

        assertAll(
                () -> assertSame(bv, returned),
                () -> assertArrayEquals(
                        new byte[] { (byte) 0x80, (byte) 0xFF, (byte) 0x01, (byte) 0x02 },
                        java.util.Arrays.copyOf((byte[]) dataField.get(bv), (int) lengthField.get(bv)))
        );
    }
}