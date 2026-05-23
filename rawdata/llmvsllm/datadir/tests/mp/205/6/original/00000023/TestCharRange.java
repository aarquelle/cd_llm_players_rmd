import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'f');   // everything except d-f
        CharRange nonNegatedInside = CharRange.isIn('e', 'e'); // inside excluded portion
        CharRange negatedWider = CharRange.isNotIn('c', 'g');  // excludes more than negated

        assertFalse(negated.contains(nonNegatedInside));
        assertTrue(negated.contains(negatedWider));
    }
}