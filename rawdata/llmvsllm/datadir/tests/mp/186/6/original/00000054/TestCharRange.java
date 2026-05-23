import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // should reorder to a-z and be negated

        assertTrue(r.contains(CharRange.isIn('0', '9'))); // outside [a,z] so contained for negated
        assertSame(r.toString(), r.toString()); // ensure cached String instance returned
    }
}