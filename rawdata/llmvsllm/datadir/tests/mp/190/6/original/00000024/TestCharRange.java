import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f');   // everything except [c..f]
        CharRange inside = CharRange.isIn('d', 'e');       // fully inside excluded area => should NOT be contained
        CharRange outside = CharRange.isIn('a', 'b');      // fully outside excluded area => should be contained

        assertAll(
                () -> assertFalse(negated.contains(inside)),
                () -> assertTrue(negated.contains(outside))
        );
    }
}