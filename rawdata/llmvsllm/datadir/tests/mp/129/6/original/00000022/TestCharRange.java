import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange innerNonNegatedOutside = CharRange.isIn('a', 'b'); // entirely outside excluded block
        assertTrue(outerNegated.contains(innerNonNegatedOutside));

        CharRange fullNonNegated = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange innerNegated = CharRange.isNotIn('c', 'f');
        assertTrue(fullNonNegated.contains(innerNegated));
    }
}