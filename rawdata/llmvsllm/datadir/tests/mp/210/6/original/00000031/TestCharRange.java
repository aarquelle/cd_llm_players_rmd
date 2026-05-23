import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('c', 'f'); // excludes [c..f]
        CharRange posInsideExcluded = CharRange.isIn('d', 'e'); // fully inside [c..f]

        assertFalse(outerNeg.contains(posInsideExcluded));
        assertTrue(outerNeg.contains(CharRange.isIn('a', 'b')));
    }
}