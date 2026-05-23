import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // normalized to a-z, negated
        CharRange inner = CharRange.isNotIn('b', 'm'); // normalized to b-m, negated

        assertTrue(outer.contains(inner));
        assertEquals("^a-z", outer.toString());
    }
}