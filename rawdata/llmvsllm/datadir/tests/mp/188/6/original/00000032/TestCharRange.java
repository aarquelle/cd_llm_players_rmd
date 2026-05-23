import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('e', 'a'); // normalizes to a-e

        assertTrue(reversed.equals(reversed));
        assertFalse(reversed.equals(CharRange.isNotIn('a', 'e')));
    }
}