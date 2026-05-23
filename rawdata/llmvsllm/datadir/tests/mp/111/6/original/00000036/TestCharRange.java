import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // reversed; should normalize to a-z and be negated
        CharRange inner = CharRange.isIn('b', 'y');    // fully inside a-z => NOT contained by negated outer
        assertFalse(outer.contains(inner));

        CharRange disjoint = CharRange.isIn('0', '9'); // entirely outside a-z => contained by negated outer
        assertTrue(outer.contains(disjoint));
    }
}