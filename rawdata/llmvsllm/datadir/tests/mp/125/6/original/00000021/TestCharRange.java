import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange innerNonNegatedInside = CharRange.isIn('d', 'e');
        CharRange innerNonNegatedOutside = CharRange.isIn('a', 'b');

        assertFalse(outerNegated.contains(innerNonNegatedInside));
        assertTrue(outerNegated.contains(innerNonNegatedOutside));
    }
}