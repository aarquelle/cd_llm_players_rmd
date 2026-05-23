import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'f'); // all except d-f
        CharRange innerWithinExcluded = CharRange.isIn('e', 'e'); // inside excluded part
        CharRange innerOutsideExcluded = CharRange.isIn('a', 'c'); // fully outside excluded part

        assertFalse(outer.contains(innerWithinExcluded));
        assertTrue(outer.contains(innerOutsideExcluded));
    }
}