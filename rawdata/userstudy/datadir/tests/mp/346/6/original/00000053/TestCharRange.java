import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('d', 'b'); // normalizes to b-d and is negated
        assertEquals("^b-d", r.toString());
        assertTrue(r.contains('a')); // outside b-d, so included in negated range
    }
}