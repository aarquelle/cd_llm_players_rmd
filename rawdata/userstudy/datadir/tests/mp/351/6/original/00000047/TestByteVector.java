import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(10);
// U+0080 -> C2 80
bv.putUTF8("\u0080");
Field dataF = ByteVector.class.getDeclaredField("data");
dataF.setAccessible(true);
byte[] data = (byte[]) dataF.get(bv);
assertArrayEquals(new byte[] { 0, 2, (byte) 0xC2, (byte) 0x80 }, Arrays.copyOf(data, 4));
assertEquals(4, getLength(bv));
    }
}