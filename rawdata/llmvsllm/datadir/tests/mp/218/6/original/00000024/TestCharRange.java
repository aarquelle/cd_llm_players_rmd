import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f');  // excludes d..f
        CharRange innerNegated = CharRange.isNotIn('e', 'e');  // excludes only e

        assertFalse(outerNegated.contains(innerNegated));
        assertTrue(CharRange.isIn((char) 0, Character.MAX_VALUE).contains(innerNegated));
    }
}