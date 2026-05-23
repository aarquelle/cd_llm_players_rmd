import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('z', 'a'); // reversed, becomes ^a-z
        CharRange inside = CharRange.isIn('b', 'y');
        assertFalse(negated.contains(inside));

        CharRange outside = CharRange.isIn('0', '9');
        assertTrue(negated.contains(outside));
    }
}