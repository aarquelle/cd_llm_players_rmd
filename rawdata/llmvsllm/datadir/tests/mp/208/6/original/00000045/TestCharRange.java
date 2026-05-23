import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('e', 'a'); // should normalize to a-e
        CharRange negated = CharRange.isNotIn('d', 'f'); // everything except d-f, so it includes a-e? no

        assertEquals("a-e", normalized.toString());
        assertFalse(negated.contains(normalized));
    }
}