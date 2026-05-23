import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('e', 'a');
        assertEquals("a-e", reversed.toString());

        assertTrue(CharRange.isNotIn('b', 'd').contains(CharRange.isIn('a', 'a')));
    }
}