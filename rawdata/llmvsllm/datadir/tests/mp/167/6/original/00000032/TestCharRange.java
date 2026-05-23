import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange base = CharRange.isNotIn('d', 'h'); // everything except [d-h]

        assertAll(
                () -> assertTrue(base.contains(CharRange.isIn('a', 'c'))),     // disjoint normal range should be contained
                () -> assertFalse(base.contains(CharRange.isNotIn('c', 'i')))  // wider negated range should NOT be contained
        );
    }
}