import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('z', 'a'); // should normalize to a-z and be negated

        assertEquals("^a-z", range.toString());
        assertSame(range.toString(), range.toString()); // cached String instance must be reused
    }
}