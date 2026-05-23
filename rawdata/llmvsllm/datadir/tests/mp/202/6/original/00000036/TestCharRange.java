import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // normalized to ^a-z
        assertTrue(r.contains(CharRange.isIn('0', '9')));
        assertEquals("^a-z", r.toString() + "|" + r.contains(CharRange.isIn('b', 'c')));
    }
}