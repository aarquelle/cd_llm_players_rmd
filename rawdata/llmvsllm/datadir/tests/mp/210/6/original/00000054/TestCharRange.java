import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange reversed = CharRange.isIn('e', 'a'); // should normalize to a-e
        assertTrue(reversed.contains(CharRange.isNot('b'))); // only true for full range 0..MAX

        CharRange negated = CharRange.isNotIn('c', 'f');
        assertTrue(negated.contains(CharRange.isIn('a', 'b'))); // disjoint before start => contained
    }
}