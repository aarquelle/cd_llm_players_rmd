import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // normalizes to a-z, negated

        assertTrue(r.contains(CharRange.isIn('0', '9'))); // fully outside => contained by negated range
        assertSame(r.toString(), r.toString()); // cached toString instance
    }
}