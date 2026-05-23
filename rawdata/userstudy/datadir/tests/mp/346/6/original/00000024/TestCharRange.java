import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r1 = CharRange.isIn('b', 'h');      // non-negated
        CharRange r2 = CharRange.isNotIn('b', 'h');   // negated

        assertEquals(83 + 'b' + 7 * 'h' + 0, r1.hashCode());
        assertEquals(83 + 'b' + 7 * 'h' + 1, r2.hashCode());
    }
}