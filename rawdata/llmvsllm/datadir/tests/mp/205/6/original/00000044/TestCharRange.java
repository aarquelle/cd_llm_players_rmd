import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('z', 'a');

        assertAll(
                () -> assertTrue(range.isNegated(), "Range must be negated"),
                () -> assertEquals("^a-z", range.toString(), "Reversed bounds must be swapped and reflected in toString")
        );
    }
}