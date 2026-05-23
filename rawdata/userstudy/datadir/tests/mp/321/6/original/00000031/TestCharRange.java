import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f');

        // For negated outer & negated inner: contains iff outer.start >= inner.start && outer.end <= inner.end
        CharRange innerNegatedWider = CharRange.isNotIn('b', 'h'); // should be contained
        CharRange innerNegatedNarrower = CharRange.isNotIn('e', 'e'); // should NOT be contained

        assertTrue(outerNegated.contains(innerNegatedWider));
        assertFalse(outerNegated.contains(innerNegatedNarrower));
    }
}