import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'a'); // constructor must reorder to a-d, negated
        CharRange inner = CharRange.isIn('b', 'c');

        assertFalse(outerNeg.contains(inner));
        assertEquals("^a-d", outerNeg.toString());
    }
}