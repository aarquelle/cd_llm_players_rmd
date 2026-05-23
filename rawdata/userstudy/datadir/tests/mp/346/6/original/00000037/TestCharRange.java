import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'a'); // normalizes to ^a-e
        CharRange inner = CharRange.isIn('f', 'g');     // completely outside a-e

        assertTrue(outer.contains(inner));
        assertEquals("^a-e", outer.toString());
    }
}