import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('d', 'f');     // everything except d-f
        CharRange inner = CharRange.isIn('d', 'd');           // exactly the excluded edge
        assertFalse(outerNeg.contains(inner));

        CharRange innerOutside = CharRange.isIn('a', 'c');    // entirely outside excluded range
        assertTrue(outerNeg.contains(innerOutside));
    }
}