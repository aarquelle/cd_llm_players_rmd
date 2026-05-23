import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'd'); // excludes [c-d]
        CharRange innerNonNegatedDisjoint = CharRange.isIn('a', 'b'); // fully outside [c-d]
        CharRange innerNonNegatedOverlaps = CharRange.isIn('b', 'c'); // touches excluded boundary at 'c'

        assertTrue(outerNegated.contains(innerNonNegatedDisjoint));
        assertFalse(outerNegated.contains(innerNonNegatedOverlaps));
    }
}