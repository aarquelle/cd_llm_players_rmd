import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('a', 'e');
        CharRange reversed = CharRange.isIn('e', 'a');
        CharRange negated = CharRange.isNotIn('a', 'e');

        assertEquals(83 + 'a' + 7 * 'e', normalized.hashCode());
        assertEquals(reversed.hashCode() + 1, negated.hashCode());
    }
}