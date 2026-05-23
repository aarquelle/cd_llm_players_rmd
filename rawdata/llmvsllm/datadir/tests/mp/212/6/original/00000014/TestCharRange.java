import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('c', 'm');
        CharRange inner = CharRange.isNotIn('e', 'h');

        assertFalse(outer.contains(inner));
        assertTrue(inner.contains(outer));
    }
}