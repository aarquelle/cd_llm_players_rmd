import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('e', 'a'); // constructor should reorder to a-e, negated

        assertFalse(r.contains('b')); // inside original range -> negated should be false
        assertEquals("^a-e", r.toString()); // checks reordering + negation + dash formatting
    }
}