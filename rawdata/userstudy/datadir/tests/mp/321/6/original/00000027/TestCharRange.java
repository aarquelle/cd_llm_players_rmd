import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'f'); // everything except d-f
        CharRange insideExcluded = CharRange.isIn('e', 'e'); // should NOT be contained

        assertFalse(negated.contains(insideExcluded));

        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange otherNegated = CharRange.isNotIn('a', 'b');
        assertTrue(full.contains(otherNegated));
    }
}