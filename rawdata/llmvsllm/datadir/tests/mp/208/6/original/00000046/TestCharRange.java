import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // reversed -> a-z, negated
        CharRange inner = CharRange.isNotIn('c', 'b'); // reversed -> b-c, negated

        assertTrue(outer.contains(inner));
        assertEquals("^a-z", outer.toString());
    }
}