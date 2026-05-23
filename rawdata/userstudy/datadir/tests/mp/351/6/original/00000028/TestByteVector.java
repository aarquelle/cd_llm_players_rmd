import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(1);
Field dataF = ByteVector.class.getDeclaredField("data");
dataF.setAccessible(true);
byte[] before = (byte[]) dataF.get(bv);
bv.putShort(0x0102);
byte[] after = (byte[]) dataF.get(bv);
Field lenF = ByteVector.class.getDeclaredField("length");
lenF.setAccessible(true);
int len = (int) lenF.get(bv);
assertTrue(after.length > before.length);
assertEquals(2, len);
    }
}