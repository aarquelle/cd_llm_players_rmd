import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // normalizes to ^a-z
        assertTrue(r.contains(CharRange.is('0')));
        assertFalse(r.contains(CharRange.is('m')));
    }
}