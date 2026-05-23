import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f'); // excludes d..f
        CharRange innerNormal = CharRange.isIn('a', 'c');     // entirely outside excluded band -> should be contained

        assertTrue(outerNegated.contains(innerNormal));
        assertFalse(CharRange.isIn(Character.MAX_VALUE, (char) 0).contains(CharRange.isNot('x')));
    }
}