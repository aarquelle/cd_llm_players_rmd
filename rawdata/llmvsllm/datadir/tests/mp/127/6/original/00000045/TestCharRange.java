import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // reversed to a-z, negated => "^a-z"
        CharRange inner = CharRange.isIn('0', '9');

        assertTrue(outer.contains(inner));

        String s1 = outer.toString();
        String s2 = outer.toString();
        assertSame(s1, s2);
    }
}