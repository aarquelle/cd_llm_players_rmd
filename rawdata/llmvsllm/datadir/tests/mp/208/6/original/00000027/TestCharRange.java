import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f');       // everything except d-f
        CharRange innerNonNeg = CharRange.isIn('a', 'c');       // fully outside excluded area => should be contained

        CharRange outerNonNeg = CharRange.isIn('a', 'z');       // not full universe
        CharRange innerNeg = CharRange.isNot('m');              // negated range should only be contained by full universe

        assertAll(
                () -> assertTrue(outerNeg.contains(innerNonNeg)),
                () -> assertFalse(outerNonNeg.contains(innerNeg))
        );
    }
}