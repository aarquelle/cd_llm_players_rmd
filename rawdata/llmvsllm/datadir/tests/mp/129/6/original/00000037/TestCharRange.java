import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'a'); // should normalize to a-e and be negated
        assertTrue(outer.contains(CharRange.isIn('f', 'g'))); // entirely outside => contained for negated
        assertFalse(outer.contains(CharRange.isIn('a', 'a'))); // boundary inside => not contained for negated
    }
}