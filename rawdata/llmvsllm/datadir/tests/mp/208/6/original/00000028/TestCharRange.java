import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f');
        CharRange innerNonNeg = CharRange.isIn('a', 'c');
        assertTrue(outerNeg.contains(innerNonNeg));

        CharRange outerNonNeg = CharRange.isIn('a', 'z');
        CharRange innerNeg = CharRange.isNot('m');
        assertFalse(outerNonNeg.contains(innerNeg));
    }
}