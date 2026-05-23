import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('e', 'a'); // should normalize to a-e and be negated
        CharRange inside = CharRange.isIn('b', 'd');     // fully inside a-e => should NOT be contained by ^a-e
        assertFalse(negated.contains(inside));

        CharRange outside = CharRange.isIn('f', 'g');    // fully outside a-e => should be contained by ^a-e
        assertTrue(negated.contains(outside));
    }
}