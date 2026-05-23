import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'a'); // reversed to a-d, negated
        assertTrue(outer.contains(CharRange.isIn('e', 'f')));
        assertEquals("^a-d", outer.toString());
    }
}