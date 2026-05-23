import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange inner = CharRange.isIn('a', 'b');           // fully outside excluded region
        CharRange touching = CharRange.isIn('b', 'c');        // touches boundary (includes 'c')

        assertTrue(outerNegated.contains(inner));
        assertFalse(outerNegated.contains(touching));
    }
}