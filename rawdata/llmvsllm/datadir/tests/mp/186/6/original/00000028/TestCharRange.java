import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange swapped = CharRange.isIn('z', 'a');      // should normalize to a-z
        CharRange normal = CharRange.isIn('a', 'z');
        CharRange negated = CharRange.isNotIn('a', 'z');

        assertEquals(83 + 'a' + 7 * 'z', swapped.hashCode());
        assertNotEquals(normal.hashCode(), negated.hashCode());
    }
}