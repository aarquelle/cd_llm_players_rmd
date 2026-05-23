import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('d', 'f');   // excludes d..f
        CharRange insideExcluded = CharRange.isIn('e', 'e');    // within excluded block -> should NOT be contained
        CharRange disjoint = CharRange.isIn('a', 'c');          // fully outside excluded block -> should be contained

        assertFalse(negatedOuter.contains(insideExcluded));
        assertTrue(negatedOuter.contains(disjoint));
    }
}