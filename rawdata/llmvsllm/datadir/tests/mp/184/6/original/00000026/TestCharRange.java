import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'b'); // normalizes to b-d, negated
        CharRange inner = CharRange.isIn('b', 'd');

        String s1 = outer.toString();
        String s2 = outer.toString();

        assertFalse(outer.contains(inner));
        assertSame(s1, s2);
    }
}