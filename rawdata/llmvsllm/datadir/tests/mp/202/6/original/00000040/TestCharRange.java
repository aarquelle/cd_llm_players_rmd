import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('d', 'a'); // should normalize to a-d and be negated
        assertEquals("^a-d", r.toString());
        assertFalse(r.contains('b'));
    }
}