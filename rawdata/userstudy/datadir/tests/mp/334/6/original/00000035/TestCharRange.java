import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f');          // everything except c-f
        CharRange insideExcluded = CharRange.isIn('d', 'e');      // within excluded -> not contained
        CharRange outsideExcluded = CharRange.isIn('a', 'b');     // outside excluded -> contained

        assertFalse(negated.contains(insideExcluded));
        assertTrue(negated.contains(outsideExcluded));
    }
}