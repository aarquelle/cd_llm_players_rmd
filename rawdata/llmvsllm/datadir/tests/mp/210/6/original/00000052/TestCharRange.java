import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'm'); // everything except d..m
        CharRange insideExcluded = CharRange.isIn('e', 'f');
        CharRange completelyOutsideExcluded = CharRange.isIn('a', 'c');

        assertFalse(negated.contains(insideExcluded));
        assertTrue(negated.contains(completelyOutsideExcluded));
    }
}