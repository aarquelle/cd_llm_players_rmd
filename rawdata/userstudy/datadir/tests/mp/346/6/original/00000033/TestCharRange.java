import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // reversed inputs must normalize to a-z, negated
        assertTrue(r.contains(CharRange.is('m'))); // inside excluded band => should be contained by negated range check logic
        assertEquals("^a-z", r.toString()); // verifies normalization, negation marker, and hyphenated form
    }
}