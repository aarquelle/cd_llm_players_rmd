import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('d', 'b'); // should reorder to b-d and be negated
        CharRange inside = CharRange.isIn('b', 'c');

        assertFalse(neg.contains(inside));

        String s1 = neg.toString();
        String s2 = neg.toString();
        assertSame(s1, s2);
    }
}