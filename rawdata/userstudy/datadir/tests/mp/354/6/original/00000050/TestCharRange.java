import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('a', 'e');
        assertTrue(normalized.equals(CharRange.isIn('e', 'a')));
        assertFalse(normalized.equals(CharRange.isNotIn('e', 'a')));
    }
}