import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isIn('e', 'a'); // should normalize to a-e

        assertTrue("Expected normalized range to contain 'b'", r.contains('b'));
        assertEquals("a-e", r.toString());
    }
}