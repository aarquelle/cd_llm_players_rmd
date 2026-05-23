import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('z', 'a'); // constructor swaps to a-z, negated
        int expected = 83 + 'a' + 7 * 'z' + 1;
        assertEquals(expected, range.hashCode());
        assertNotEquals(expected, CharRange.isIn('a', 'z').hashCode());
    }
}