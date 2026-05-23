import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f'); // everything except d-f
        CharRange innerNonOverlapping = CharRange.isIn('a', 'c'); // entirely outside excluded segment
        CharRange innerNegatedWider = CharRange.isNotIn('b', 'h'); // excludes a wider range than outer

        assertAll(
                () -> assertTrue(outerNegated.contains(innerNonOverlapping)),
                () -> assertFalse(outerNegated.contains(innerNegatedWider))
        );
    }
}