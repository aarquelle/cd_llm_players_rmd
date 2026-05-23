import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange aToE = CharRange.isIn('e', 'a'); // must reorder to a-e
        CharRange notB = CharRange.isNot('b');     // negated single char

        assertTrue(aToE.contains(CharRange.isIn('a', 'e')));  // verifies reordering + contains(CharRange) logic
        assertFalse(aToE.contains(notB));                      // only full range 0..MAX can contain a negated range
    }
}