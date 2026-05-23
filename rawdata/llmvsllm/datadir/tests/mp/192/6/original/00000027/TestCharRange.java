import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('z', 'a'); // normalize to a-z, negated

        boolean containsDigits = neg.contains(CharRange.isIn('0', '9'));
        boolean containsBC = neg.contains(CharRange.isIn('b', 'c'));
        assertTrue(containsDigits);
        assertFalse(containsBC);
    }
}