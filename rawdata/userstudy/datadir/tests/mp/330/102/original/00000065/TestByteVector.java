import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        ByteVector bv = new ByteVector(0);
        bv.putUTF8("АБ");
        assertEquals(6, bv.length);
        assertArrayEquals(new byte[]{0, 4, -48, -112, -48, -1}, bv.data);
    }
}