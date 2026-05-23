import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('z', 'a'); // should normalize to [a-z] negated
        CharRange innerNegated = CharRange.isNotIn('b', 'y');

        assertTrue(outerNegated.contains(innerNegated));
        assertEquals("^a-z", outerNegated.toString());
    }
}