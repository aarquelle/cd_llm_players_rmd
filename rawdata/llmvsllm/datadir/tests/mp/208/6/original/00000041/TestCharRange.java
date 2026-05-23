import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('m', 'c'); // should normalize to c-m and be negated
        CharRange innerTouchingBoundary = CharRange.isIn('c', 'e'); // overlaps at boundary 'c'

        assertEquals("^c-m", outer.toString());
        assertFalse(outer.contains(innerTouchingBoundary));
    }
}