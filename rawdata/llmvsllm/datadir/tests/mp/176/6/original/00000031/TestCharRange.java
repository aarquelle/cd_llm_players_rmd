import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('d', 'a'); // constructor must reorder to a-d
        assertEquals(83 + 'a' + 7 * 'd' + 1, r.hashCode());
        assertNotEquals(r.hashCode(), CharRange.isIn('a', 'd').hashCode());
    }
}