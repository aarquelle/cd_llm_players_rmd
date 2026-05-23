import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'b'); // order should normalize to b-d, negated => ^b-d
        CharRange innerOutside = CharRange.isIn('x', 'y');

        assertTrue(outer.contains(innerOutside)); // for negated outer: range must be entirely outside b-d
        assertSame(outer.toString(), outer.toString()); // cached string should be reused
    }
}