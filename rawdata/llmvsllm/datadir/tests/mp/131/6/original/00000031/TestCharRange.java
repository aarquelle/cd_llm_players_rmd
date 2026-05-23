import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // should normalize to a-z and remain negated
        assertEquals("^a-z", r.toString());

        // For negated r (^a-z), it should contain a disjoint positive range outside a-z
        assertTrue(r.contains(CharRange.isIn('0', '9')));
    }
}