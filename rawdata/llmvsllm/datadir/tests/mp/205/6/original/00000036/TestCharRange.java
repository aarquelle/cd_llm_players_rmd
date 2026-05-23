import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // normalizes to ^a-z
        CharRange disjoint = CharRange.isIn('0', '9');  // entirely outside a-z

        assertEquals("^a-z", outer.toString());
        assertTrue(outer.contains(disjoint));
    }
}