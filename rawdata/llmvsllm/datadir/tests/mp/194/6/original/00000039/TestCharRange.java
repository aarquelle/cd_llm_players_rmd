import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // should reorder to a-z and be negated
        assertEquals("^a-z", outer.toString());
        assertTrue(outer.contains(CharRange.is('0'))); // '0' is outside a-z, so should be contained in negated range
    }
}