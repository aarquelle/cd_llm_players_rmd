import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange insideExcluded = CharRange.isIn('d', 'e'); // fully within c-f -> should NOT be contained

        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE); // full universe
        CharRange negatedSingle = CharRange.isNot('x'); // all except x -> should be contained by full

        assertFalse(negated.contains(insideExcluded));
        assertTrue(full.contains(negatedSingle));
    }
}