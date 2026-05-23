import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(3);
Field dataF = ByteVector.class.getDeclaredField("data");
dataF.setAccessible(true);
byte[] data = (byte[]) dataF.get(bv);
Field lenF = ByteVector.class.getDeclaredField("length");
lenF.setAccessible(true);
int len = (int) lenF.get(bv);
assertEquals(3, data.length);
assertEquals(0, len);
    }
}