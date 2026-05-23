import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('e', 'a');
        CharRange negated = CharRange.isNotIn('d', 'f');

        assertEquals("a-e", normalized.toString());
        assertTrue(negated.contains(normalized));
    }
}