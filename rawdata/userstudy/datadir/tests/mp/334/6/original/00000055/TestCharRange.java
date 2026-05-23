import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange a = CharRange.isNotIn('d', 'f'); // excludes d..f

        // negated vs negated: 'd'..'f' is within 'c'..'g' so should be false (not contained)
        assertFalse(a.contains(CharRange.isNotIn('c', 'g')));

        // negated vs non-negated: overlaps excluded boundary, so should be false
        assertFalse(a.contains(CharRange.isIn('f', 'f')));
    }
}