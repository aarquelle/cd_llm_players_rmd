import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('b', 'd'); // everything except b-d
        CharRange inner = CharRange.isIn('b', 'c');      // within excluded region -> should NOT be contained
        CharRange outside = CharRange.isIn('e', 'f');    // outside excluded region -> should be contained

        assertFalse(negated.contains(inner));
        assertTrue(negated.contains(outside));
    }
}