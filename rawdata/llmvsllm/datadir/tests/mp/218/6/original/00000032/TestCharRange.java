import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('e', 'a'); // should reorder to a-e and be negated -> ^a-e

        assertEquals("^a-e", range.toString());

        String cached = range.toString();
        assertTrue(cached == range.toString());
    }
}