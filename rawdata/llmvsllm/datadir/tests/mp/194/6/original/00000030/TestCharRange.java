import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange base = CharRange.isNotIn('e', 'a'); // should normalize to a-e and be negated
        CharRange inside = CharRange.isIn('b', 'd');

        assertEquals("^a-e", base.toString());
        assertFalse(base.contains(inside));
    }
}