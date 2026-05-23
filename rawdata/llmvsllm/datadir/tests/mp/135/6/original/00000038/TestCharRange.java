import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('b', 'd'); // excludes b..d
        CharRange inner = CharRange.isIn('c', 'c'); // inside excluded area
        CharRange disjoint = CharRange.isIn('a', 'a'); // outside excluded area

        assertFalse(outerNegated.contains(inner));
        assertTrue(outerNegated.contains(disjoint));
    }
}