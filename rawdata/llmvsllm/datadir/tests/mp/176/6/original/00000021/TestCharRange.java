import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('b', 'd'); // contains everything except b..d
        CharRange insideExcluded = CharRange.isIn('c', 'c');
        assertFalse(negated.contains(insideExcluded));

        CharRange outsideExcluded = CharRange.isIn('a', 'a');
        assertTrue(negated.contains(outsideExcluded));
    }
}