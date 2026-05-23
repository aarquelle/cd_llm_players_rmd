import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isIn('d', 'a'); // constructor should reorder to a-d
        int expected = 83 + 'a' + 7 * 'd' + 0;
        assertEquals(expected, r.hashCode());

        assertNotEquals(CharRange.isIn('a', 'd').hashCode(), CharRange.isNotIn('a', 'd').hashCode());
    }
}