import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange innerNonNegOutside = CharRange.isIn('a', 'b'); // fully outside excluded block
        CharRange innerNegNarrower = CharRange.isNotIn('d', 'e'); // excludes less, thus is larger set

        assertAll(
                () -> assertTrue(outerNeg.contains(innerNonNegOutside)),
                () -> assertFalse(outerNeg.contains(innerNegNarrower))
        );
    }
}