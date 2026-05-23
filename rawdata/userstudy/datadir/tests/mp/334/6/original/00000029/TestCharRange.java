import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f'); // contains everything except d..f
        CharRange innerNonNegatedDisjoint = CharRange.isIn('a', 'c'); // fully outside excluded block
        assertTrue(outerNegated.contains(innerNonNegatedDisjoint));

        CharRange universe = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedInner = CharRange.isNotIn('x', 'z');
        assertTrue(universe.contains(negatedInner));
    }
}