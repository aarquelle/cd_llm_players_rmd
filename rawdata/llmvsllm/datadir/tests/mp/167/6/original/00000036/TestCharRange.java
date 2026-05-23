import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('e', 'a'); // should normalize to a-e and be negated
        assertEquals(83 + 'a' + 7 * 'e' + 1, r.hashCode());
        assertNotEquals(CharRange.isIn('a', 'e').hashCode(), r.hashCode());
    }
}