import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('m', 'p');      // excludes m-p
        CharRange inside = CharRange.isIn('n', 'o');      // fully inside excluded region
        CharRange outside = CharRange.isIn('a', 'b');     // fully outside excluded region

        assertFalse(neg.contains(inside));
        assertTrue(neg.contains(outside));
    }
}