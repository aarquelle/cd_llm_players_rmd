import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isIn('z', 'a'); // should reorder to a-z
        CharRange neg = CharRange.isNotIn('m', 'o');

        assertEquals("a-z", r.toString());
        assertFalse(neg.contains(CharRange.isIn('n', 'n')));
    }
}