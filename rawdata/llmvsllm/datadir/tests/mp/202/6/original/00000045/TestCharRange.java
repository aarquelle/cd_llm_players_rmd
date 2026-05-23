import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('e', 'a');
        CharRange negated = CharRange.isNotIn('a', 'c');

        assertEquals("a-e", reversed.toString());
        assertTrue(negated.contains(CharRange.isIn('x', 'z')));
    }
}