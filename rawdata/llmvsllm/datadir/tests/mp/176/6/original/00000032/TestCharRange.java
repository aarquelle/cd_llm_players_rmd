import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange nonNegated = CharRange.isIn('b', 'd');
        CharRange negated = CharRange.isNotIn('b', 'd');

        assertEquals(83 + 'b' + 7 * 'd', nonNegated.hashCode());
        assertEquals(nonNegated.hashCode() + 1, negated.hashCode());
    }
}