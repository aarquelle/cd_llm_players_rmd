import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isIn('e', 'a'); // should normalize to a-e
        int expected = 83 + 'a' + 7 * 'e';
        assertEquals(expected, range.hashCode());

        assertEquals(expected + 1, CharRange.isNotIn('a', 'e').hashCode());
    }
}