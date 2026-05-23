import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('d', 'b'); // should normalize to b-d, negated
        assertEquals("^b-d", neg.toString());

        assertFalse(neg.contains(CharRange.isIn('a', 'c'))); // overlaps b-d, so not fully contained by negated range
    }
}