import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange innerNonNegOutsideGap = CharRange.isIn('a', 'b');
        CharRange innerNonNegOverlapsGap = CharRange.isIn('b', 'c');

        assertAll(
            () -> assertTrue(negatedOuter.contains(innerNonNegOutsideGap)),
            () -> assertFalse(negatedOuter.contains(innerNonNegOverlapsGap))
        );
    }
}