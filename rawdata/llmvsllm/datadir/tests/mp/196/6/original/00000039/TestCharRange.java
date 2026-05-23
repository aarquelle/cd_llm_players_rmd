import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isIn('b', 'z');
        assertEquals(83 + 'b' + 7 * 'z' + 0, range.hashCode());

        CharRange negated = CharRange.isNotIn('b', 'z');
        assertNotEquals(range.hashCode(), negated.hashCode());
    }
}