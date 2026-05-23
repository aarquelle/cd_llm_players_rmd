import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('z', 'a'); // constructor reverses to a-z
        assertEquals(83 + 'a' + 7 * 'z' + 0, reversed.hashCode());

        CharRange negated = CharRange.isNotIn('a', 'z');
        assertNotEquals(reversed.hashCode(), negated.hashCode());
    }
}