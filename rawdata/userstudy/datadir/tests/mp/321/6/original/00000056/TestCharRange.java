import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange all = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedAny = CharRange.isNotIn('c', 'f');
        CharRange nonNegatedNonOverlap = CharRange.isIn('g', 'h');

        assertTrue(all.contains(negatedAny));
        assertTrue(negatedAny.contains(nonNegatedNonOverlap));
    }
}