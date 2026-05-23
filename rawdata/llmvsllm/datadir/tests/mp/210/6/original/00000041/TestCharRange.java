import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('d', 'b'); // reordered to b-d, negated
        assertEquals("^b-d", r.toString());
        assertTrue(r.contains(CharRange.is('a')));
    }
}