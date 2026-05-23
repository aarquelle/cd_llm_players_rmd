import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange a = CharRange.isNotIn('d', 'a'); // normalized to a-d, negated
        CharRange b = CharRange.isNotIn('a', 'd'); // same logical range
        CharRange c = CharRange.isIn('a', 'd');    // differs only by negation

        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }
}