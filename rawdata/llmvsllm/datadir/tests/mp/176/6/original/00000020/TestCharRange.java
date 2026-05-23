import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange baseNeg = CharRange.isNotIn('d', 'f'); // everything except d-f

        assertTrue(baseNeg.contains(CharRange.isIn('a', 'c')));      // fully outside excluded region
        assertFalse(baseNeg.contains(CharRange.isNotIn('e', 'g')));  // requires base to allow d..g, but it excludes d..f
    }
}