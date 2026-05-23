import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange base = CharRange.isNotIn('d', 'h'); // everything except [d-h]

        assertTrue(base.contains(CharRange.isNotIn('e', 'g'))); // negated contains a smaller excluded window
        assertFalse(base.contains(CharRange.isNotIn('c', 'i'))); // negated does not contain a larger excluded window
    }
}