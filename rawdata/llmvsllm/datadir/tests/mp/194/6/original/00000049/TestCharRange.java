import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'b'); // reordered to b-d, negated
        CharRange inside = CharRange.isIn('b', 'c');

        assertFalse(outer.contains(inside));
        assertEquals("^b-d", outer.toString());
    }
}