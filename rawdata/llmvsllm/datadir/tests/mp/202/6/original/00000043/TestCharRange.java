import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('e', 'a'); // should normalize to a-e, negated => ^a-e
        assertTrue(r.contains(CharRange.isIn('f', 'z')));
        assertEquals("^a-e", r.toString());
    }
}