import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('d', 'b'); // normalizes to b-d and is negated
        CharRange inside = CharRange.isIn('b', 'c');
        CharRange outside = CharRange.isIn('e', 'f');

        assertEquals("^b-d", neg.toString());
        assertFalse(neg.contains(inside));
    }
}