import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('z', 'a');
        CharRange nonNegated = CharRange.isIn('z', 'a');

        assertAll(
                () -> assertTrue(negated.isNegated()),
                () -> assertFalse(nonNegated.isNegated())
        );
    }
}