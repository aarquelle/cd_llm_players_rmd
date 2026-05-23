import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('d', 'a'); // normalizes to a-d, negated

        int expected = 83 + 'a' + 7 * 'd' + 1;
        assertEquals(expected, r.hashCode());
    }
}