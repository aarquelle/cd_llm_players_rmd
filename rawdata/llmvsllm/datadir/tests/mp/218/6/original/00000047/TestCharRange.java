import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('b', 'd'); // same as isNotIn('d','b') due to swap in ctor
        CharRange inner = CharRange.isNotIn('a', 'f');

        String s1 = outer.toString();
        String s2 = outer.toString();

        assertTrue("Negated containment must follow start>=other.start && end<=other.end when both negated",
                outer.contains(inner));
        assertSame("toString should return cached String instance on subsequent calls", s1, s2);
    }
}