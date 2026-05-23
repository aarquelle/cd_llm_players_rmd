import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(3); // force enlarge
        ByteVector returned = bv.putInt(0x80FF0102);

        java.lang.reflect.Field lengthField = ByteVector.class.getDeclaredField("length");
        lengthField.setAccessible(true);
        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);

        int len = (Integer) lengthField.get(bv);
        byte[] data = (byte[]) dataField.get(bv);

        assertSame(bv, returned);
        assertArrayEquals(new byte[] { (byte) 0x80, (byte) 0xFF, (byte) 0x01, (byte) 0x02 },
                java.util.Arrays.copyOf(data, len));
    }
}