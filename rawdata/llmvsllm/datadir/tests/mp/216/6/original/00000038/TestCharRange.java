import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange innerNormalOutside = CharRange.isIn('a', 'b'); // fully outside excluded block
        CharRange innerNegatedWider = CharRange.isNotIn('b', 'g'); // excludes a wider block than outer

        assertTrue(outerNegated.contains(innerNormalOutside));
        assertFalse(outerNegated.contains(innerNegatedWider));
    }
}