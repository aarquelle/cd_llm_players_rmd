import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestByteVector {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        var v1 = new ByteVector();
        var v2 = new ByteVector(64);
        assertArrayEquals(v1.data, v2.data);
    }
}