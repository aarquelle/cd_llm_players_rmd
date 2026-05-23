import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(4);
bv.putInt(0x01020304);
Field dataF = ByteVector.class.getDeclaredField("data");
dataF.setAccessible(true);
byte[] data = (byte[]) dataF.get(bv);
assertArrayEquals(new byte[] { 1, 2, 3, 4 }, Arrays.copyOf(data, 4));
assertEquals(4, getLength(bv));
    }
}