import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // should normalize to a-z and be negated
        CharRange inner = CharRange.isIn('b', 'c');

        assertTrue(outer.contains(inner));
        assertEquals("^a-z", outer.toString());
    }
}