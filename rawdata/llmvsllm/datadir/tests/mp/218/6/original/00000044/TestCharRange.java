import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('z', 'a'); // normalizes to ^a-z
        CharRange innerNegatedWider = CharRange.isNotIn('b', 'y'); // wider complement than ^a-z

        assertFalse(outerNegated.contains(innerNegatedWider));
        assertEquals("^a-z", outerNegated.toString());
    }
}