import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // normalized to a-z, negated
        boolean containsInnerRange = r.contains(CharRange.isIn('b', 'y'));
        boolean containsMiddleChar = r.contains('m');

        assertTrue(containsInnerRange);
        assertSame(r.toString(), r.toString());
    }
}