import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'f'); // everything except d-f

        // range.end < start should be true here (c < d)
        assertTrue(outer.contains(CharRange.isIn('a', 'c')));

        // both negated: must satisfy start >= range.start AND end <= range.end (d>=d, f<=h)
        assertTrue(outer.contains(CharRange.isNotIn('d', 'h')));
    }
}