import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f');
        CharRange insideExcluded = CharRange.isIn('c', 'd');
        CharRange completelyOutside = CharRange.isIn('a', 'b');

        assertFalse(negated.contains(insideExcluded));
        assertTrue(negated.contains(completelyOutside));
    }
}