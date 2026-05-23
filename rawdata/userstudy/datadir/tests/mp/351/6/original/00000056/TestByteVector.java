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
byte[] before = (byte[]) dataF.get(bv);
bv.putUTF8("Hi");
byte[] after = (byte[]) dataF.get(bv);
assertTrue(after.length > before.length);
assertEquals(4, getLength(bv));
    }
}