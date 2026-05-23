import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        bv.putByte(0xAB);
        ByteVector ret2 = bv.putByte(0xCD);

        Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertArrayEquals(new byte[] { (byte) 0xAB, (byte) 0xCD }, new byte[] { data[0], data[1] });
        assertSame(bv, ret2);
    }
}