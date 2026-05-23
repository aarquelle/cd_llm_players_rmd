import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('z', 'a'); // constructor should reorder to a-z, then negate
        assertTrue(range.contains('0'));               // outside a-z => contained when negated
        assertFalse(range.contains('m'));              // inside a-z => not contained when negated
    }
}