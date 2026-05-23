import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f'); // excludes c..f
        CharRange innerNonNegatedDisjoint = CharRange.isIn('a', 'b'); // entirely outside excluded block => contained
        assertTrue(outerNegated.contains(innerNonNegatedDisjoint));

        CharRange outerAll = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange innerNegated = CharRange.isNotIn('m', 'n');
        assertTrue(outerAll.contains(innerNegated));
    }
}