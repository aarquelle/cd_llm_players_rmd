import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange a = CharRange.isNotIn('d', 'f'); // excludes d..f

        // For negated-vs-negated: true when this excluded block is inside other's excluded block
        assertTrue(a.contains(CharRange.isNotIn('e', 'e')));

        // For negated-vs-nonNegated: true only if the whole range lies entirely outside excluded block
        assertFalse(a.contains(CharRange.isIn('f', 'f'))); // touches boundary -> should be false
    }
}