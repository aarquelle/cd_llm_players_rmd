import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // should normalize to a-z, negated => "^a-z"

        assertFalse(r.contains('m')); // inside normalized range, but negated => false

        String s1 = r.toString();
        String s2 = r.toString();
        assertSame(s1, s2); // ensure caching; also kills mutants that remove caching
    }
}