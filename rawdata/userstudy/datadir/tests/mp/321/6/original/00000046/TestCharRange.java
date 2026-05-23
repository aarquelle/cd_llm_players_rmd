import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f');   // contains everything except c..f
        CharRange innerNonNegated = CharRange.isIn('a', 'b');   // entirely outside excluded region
        CharRange innerOverlapping = CharRange.isIn('b', 'd');  // overlaps excluded region

        assertAll(
                () -> assertTrue(outerNegated.contains(innerNonNegated)),
                () -> assertFalse(outerNegated.contains(innerOverlapping))
        );
    }
}