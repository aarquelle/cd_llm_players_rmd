import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // should normalize to a-z and be negated
        CharRange inner = CharRange.isIn('b', 'y');

        assertTrue(outer.contains(inner)); // inner is wholly inside excluded region => must be contained by negated range
        assertEquals("^a-z", outer.toString()); // verifies reversal + negation + formatting/caching path
    }
}