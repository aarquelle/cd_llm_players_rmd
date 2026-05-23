import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(2);
bv.putShort(0x1234);
Field dataF = ByteVector.class.getDeclaredField("data");
dataF.setAccessible(true);
byte[] data = (byte[]) dataF.get(bv);
assertEquals((byte) 0x12, data[0]);
assertEquals((byte) 0x34, data[1]);
    }
}