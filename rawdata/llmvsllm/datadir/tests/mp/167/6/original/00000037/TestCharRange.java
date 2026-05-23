import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f'); // contains everything except d-f
        CharRange insideExcluded = CharRange.isIn('e', 'e');   // fully inside excluded block -> should NOT be contained
        assertFalse(outerNegated.contains(insideExcluded));

        CharRange outerFull = CharRange.isIn((char) 0, Character.MAX_VALUE); // full range
        CharRange negatedInner = CharRange.isNotIn('m', 'o');                 // negated inner -> should be contained only by full range
        assertTrue(outerFull.contains(negatedInner));
    }
}