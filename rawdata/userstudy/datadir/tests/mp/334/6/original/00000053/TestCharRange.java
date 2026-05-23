import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f');   // excludes d..f
        CharRange innerNeg = CharRange.isNotIn('c', 'g');   // excludes c..g (superset exclusion)

        assertTrue(outerNeg.contains(innerNeg));
        assertFalse(outerNeg.contains(CharRange.isIn('a', 'c')));
    }
}