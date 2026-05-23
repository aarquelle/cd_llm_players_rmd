import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'm');
        assertTrue(outer.contains(CharRange.isNotIn('d', 'm')));
        assertFalse(outer.contains(CharRange.isNotIn('e', 'l')));
    }
}