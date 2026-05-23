import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('c', 'a'); // should reorder to a-c and be negated => "^a-c"
        CharRange inner = CharRange.isIn('d', 'e');    // completely outside a-c

        String s1 = outer.toString();
        String s2 = outer.toString();

        assertTrue(outer.contains(inner));
        assertSame(s1, s2);
    }
}