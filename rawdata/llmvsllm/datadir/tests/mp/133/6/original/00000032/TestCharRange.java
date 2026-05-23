import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange insideExcluded = CharRange.isIn('c', 'd'); // overlaps excluded area -> should NOT be contained
        CharRange completelyOutside = CharRange.isIn('a', 'b'); // entirely outside excluded area -> should be contained

        assertAll(
                () -> assertFalse(negated.contains(insideExcluded)),
                () -> assertTrue(negated.contains(completelyOutside))
        );
    }
}