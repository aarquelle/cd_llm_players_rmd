import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('f', 'd'); // reordered to d-f, negated
        CharRange inner = CharRange.isNotIn('e', 'c'); // reordered to c-e, negated

        assertTrue(inner.contains(outer)); // for negated/negated: c<=d and e>=f => inner contains outer
        assertEquals("^d-f", outer.toString()); // verifies negation marker and reordering
    }
}