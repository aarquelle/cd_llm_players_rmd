import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // normalized to ^a-z
        CharRange disjoint = CharRange.isIn('1', '9'); // entirely outside a-z => should be contained by negated range

        assertTrue(outer.contains(disjoint));
        assertEquals("^a-z", outer.toString());
    }
}