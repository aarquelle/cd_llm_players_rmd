import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('e', 'a'); // should normalize to ^a-e
        assertTrue(r.contains(CharRange.is('z'))); // outside should be contained for negated
        assertEquals("^a-e", r.toString()); // checks negation marker, normalization, and dash formatting
    }
}