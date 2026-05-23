import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isIn('e', 'a'); // should normalize to a-e, non-negated
        assertTrue(r.contains('c'));
        assertEquals("a-e", r.toString());
    }
}