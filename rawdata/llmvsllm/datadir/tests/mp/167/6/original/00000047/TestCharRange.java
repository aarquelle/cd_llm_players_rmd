import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('b', 'd'); // excludes [b-d]
        CharRange inner = CharRange.isIn('c', 'a');    // reordered to [a-c]

        assertEquals("^b-d", outer.toString());
        assertFalse(outer.contains(inner));
    }
}