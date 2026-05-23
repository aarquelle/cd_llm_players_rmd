import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f');
        CharRange nonNegated = CharRange.isIn('a', 'b');
        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);

        assertAll(
                () -> assertTrue(negated.contains(nonNegated)),
                () -> assertFalse(nonNegated.contains(full))
        );
    }
}