import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange outside = CharRange.isIn('a', 'b');    // fully outside c-f
        CharRange inside = CharRange.isIn('d', 'e');     // fully inside c-f (thus excluded)

        assertTrue(negated.contains(outside));
        assertFalse(negated.contains(inside));
    }
}