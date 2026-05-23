import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('f', 'd'); // ^d-f
        CharRange inner = CharRange.isNotIn('h', 'a'); // ^a-h

        assertTrue(outer.contains(inner)); // negated contains negated when outer.start>=inner.start and outer.end<=inner.end
        assertEquals("^d-f", outer.toString()); // verifies negation marker and reordering
    }
}