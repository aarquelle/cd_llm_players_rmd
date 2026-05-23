import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector(1);

        ByteVector ret1 = bv.putByte(0x12);
        ByteVector ret2 = bv.putByte(0xAB);

        java.lang.reflect.Field dataField = ByteVector.class.getDeclaredField("data");
        dataField.setAccessible(true);
        byte[] data = (byte[]) dataField.get(bv);

        assertEquals("true:true:2:18:-85", (ret1 == bv) + ":" + (ret2 == bv) + ":" + bv.length + ":" + data[0] + ":" + data[1]);
        assertTrue(data.length >= 2);
    }
}