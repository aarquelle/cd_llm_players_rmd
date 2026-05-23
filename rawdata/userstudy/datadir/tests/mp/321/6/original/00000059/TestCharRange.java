import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f');     // excludes c..f
        CharRange insideExcluded = CharRange.isIn('d', 'e'); // fully inside excluded section -> should NOT be contained
        CharRange outsideExcluded = CharRange.isIn('a', 'b'); // fully outside excluded section -> should be contained

        assertAll(
                () -> assertFalse(negated.contains(insideExcluded)),
                () -> assertTrue(negated.contains(outsideExcluded))
        );
    }
}