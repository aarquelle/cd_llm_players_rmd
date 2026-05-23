import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        var v = new ByteVector();
        var s = "A b 0 !";
        v = v.putUTF8(s);
        assertEquals(s, new String(v.data, 0, v.length));
    }
}