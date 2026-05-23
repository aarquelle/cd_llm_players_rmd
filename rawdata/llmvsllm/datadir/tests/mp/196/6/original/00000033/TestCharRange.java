import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                // Negated containing a normal range completely outside its excluded block
        assertTrue(CharRange.isNotIn('d', 'f').contains(CharRange.isIn('a', 'b')));

        // Negated containing a normal range that intersects its excluded block must be false
        assertFalse(CharRange.isNotIn('d', 'f').contains(CharRange.isIn('e', 'g')));
    }
}