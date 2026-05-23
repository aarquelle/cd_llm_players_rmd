import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('c', 'a'); // should normalize to ^a-c

        assertFalse(neg.contains('b'));              // inside excluded segment
        assertEquals("^a-c", neg.toString());        // verifies reversal + negation formatting/caching
    }
}