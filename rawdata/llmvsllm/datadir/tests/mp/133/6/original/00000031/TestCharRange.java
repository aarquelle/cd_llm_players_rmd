import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('b', 'd'); // everything except b-d
        CharRange inside = CharRange.isIn('b', 'c');     // overlaps excluded part -> should be false
        CharRange disjoint = CharRange.isIn('x', 'z');   // fully outside excluded part -> should be true

        assertFalse(negated.contains(inside));
        assertTrue(negated.contains(disjoint));
    }
}