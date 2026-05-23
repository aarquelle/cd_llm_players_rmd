import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r1 = CharRange.isNotIn('a', 'c');
        CharRange r2 = CharRange.isNotIn('c', 'a'); // normalized to same range
        CharRange differentNegation = CharRange.isIn('a', 'c');

        assertTrue(r1.equals(r2));
        assertFalse(r1.equals(differentNegation));
    }
}