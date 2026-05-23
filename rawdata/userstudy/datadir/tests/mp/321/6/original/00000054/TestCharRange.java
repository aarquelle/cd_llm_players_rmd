import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f');   // everything except c-f
        CharRange innerNegated = CharRange.isNotIn('d', 'e');   // everything except d-e
        CharRange overlapNonNegated = CharRange.isIn('b', 'd');  // overlaps c-f

        assertAll(
                () -> assertTrue(outerNegated.contains(innerNegated)),
                () -> assertFalse(outerNegated.contains(overlapNonNegated))
        );
    }
}