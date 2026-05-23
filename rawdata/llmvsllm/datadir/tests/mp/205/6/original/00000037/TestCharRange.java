import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r1 = CharRange.isIn('e', 'a'); // constructor reverses to a-e
        CharRange r2 = CharRange.isNotIn('e', 'a'); // same range but negated

        assertEquals(83 + 'a' + 7 * 'e' + 0, r1.hashCode());
        assertEquals(83 + 'a' + 7 * 'e' + 1, r2.hashCode());
    }
}