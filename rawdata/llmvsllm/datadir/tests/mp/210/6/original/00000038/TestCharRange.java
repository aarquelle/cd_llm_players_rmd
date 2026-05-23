import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // reversed order should normalize to a-z and be negated
        assertEquals("^a-z", r.toString());
        assertFalse(r.contains('m')); // within a-z should be excluded due to negation
    }
}