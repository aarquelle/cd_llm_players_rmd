import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('A', 'C'); // start=65, end=67, negated=true
        assertEquals(83 + 'A' + 7 * 'C' + 1, r.hashCode());
        assertNotEquals(CharRange.isIn('A', 'C').hashCode(), r.hashCode());
    }
}