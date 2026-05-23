import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('e', 'a'); // constructor should reorder to a-e
        int expected = 83 + 'a' + 7 * 'e' + 1;
        assertEquals(expected, r.hashCode());
        assertNotEquals(r.hashCode(), CharRange.isIn('a', 'e').hashCode());
    }
}