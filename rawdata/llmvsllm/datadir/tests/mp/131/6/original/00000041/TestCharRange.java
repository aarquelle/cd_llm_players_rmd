import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // reversed, negated => '^a-z'
        assertEquals("^a-z", r.toString());

        assertTrue(r.contains(CharRange.isIn('0', '9'))); // entirely outside a-z so contained by negated range
    }
}