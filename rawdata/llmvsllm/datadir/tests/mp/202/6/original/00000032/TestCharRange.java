import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange all = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange someNegated = CharRange.isNotIn('c', 'f');

        assertTrue(all.contains(someNegated));
        assertFalse(CharRange.isNotIn('c', 'f').contains(CharRange.isNotIn('b', 'g')));
    }
}