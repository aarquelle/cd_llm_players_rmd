import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f');       // excludes [c..f]
        CharRange innerNormalDisjoint = CharRange.isIn('a', 'b');   // entirely outside excluded block
        CharRange innerNegatedSuperset = CharRange.isNotIn('b', 'g'); // excludes a larger block; should contain outerNegated

        assertAll(
                () -> assertTrue(outerNegated.contains(innerNormalDisjoint)),
                () -> assertFalse(outerNegated.contains(innerNegatedSuperset))
        );
    }
}