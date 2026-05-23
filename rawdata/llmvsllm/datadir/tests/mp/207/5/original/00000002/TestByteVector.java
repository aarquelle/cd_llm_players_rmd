import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                ByteVector bv = new ByteVector();
        bv.putUTF8(new String(new char[200]).replace('\0', 'a'));
        assertEquals(0, bv.data[0]);
        assertEquals((byte) 200, bv.data[1]);
    }
}