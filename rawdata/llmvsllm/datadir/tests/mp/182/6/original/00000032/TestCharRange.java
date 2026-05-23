import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'a'); // should normalize to [a-d] negated
        CharRange inner = CharRange.isIn('b', 'c');

        assertSame(outer.toString(), outer.toString());
        assertTrue(outer.contains(inner));
    }
}