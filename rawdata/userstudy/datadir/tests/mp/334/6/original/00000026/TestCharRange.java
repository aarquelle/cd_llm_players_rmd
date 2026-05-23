import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'f'); // everything except [d-f]
        CharRange nonNegated = CharRange.isIn('d', 'f');

        assertTrue(negated.contains(CharRange.isIn('a', 'c')));
        assertFalse(negated.contains(nonNegated));
    }
}