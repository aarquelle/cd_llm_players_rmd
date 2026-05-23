import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f'); // excludes d..f
        assertTrue(outerNeg.contains(CharRange.isNotIn('c', 'g'))); // outer exclusion subset of inner exclusion
        assertTrue(outerNeg.contains(CharRange.isIn('a', 'c')));     // non-negated fully outside excluded block
    }
}