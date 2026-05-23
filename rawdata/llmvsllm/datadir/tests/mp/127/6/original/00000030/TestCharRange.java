import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('f', 'd'); // should reorder to d-f, negated
        CharRange inner = CharRange.isNotIn('e', 'c'); // should reorder to c-e, negated

        assertTrue(outer.contains(inner)); // for negated/negated: start >= other.start && end <= other.end
        assertEquals("^d-f", outer.toString()); // checks negation marker and reordering/cached string
    }
}