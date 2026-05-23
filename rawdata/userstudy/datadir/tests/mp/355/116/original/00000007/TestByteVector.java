import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        var v = new ByteVector(3);
        var s = "abc".getBytes();
        v = v.putUTF8("abc");
        assertArrayEquals(s, v.data);
    }
}