import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // must normalize to a-z and be negated
        CharRange inner = CharRange.isNotIn('b', 'y');

        assertEquals("^a-z", outer.toString());
        assertTrue(outer.contains(inner));
    }
}