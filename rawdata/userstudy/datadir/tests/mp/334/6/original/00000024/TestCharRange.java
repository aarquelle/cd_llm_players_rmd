import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f'); // contains everything except d..f
        CharRange innerNonNegated = CharRange.isIn('a', 'c');  // fully outside excluded block -> should be contained
        CharRange innerOverlap = CharRange.isIn('e', 'g');      // overlaps excluded block -> should NOT be contained

        assertTrue(outerNegated.contains(innerNonNegated));
        assertFalse(outerNegated.contains(innerOverlap));
    }
}