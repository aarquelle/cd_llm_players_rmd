import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('f', 'c'); // normalize to ^c-f
        boolean ok = outer.contains(CharRange.isIn('a', 'b'));
        ok = ok & (!outer.contains(CharRange.isIn('d', 'e')));
        assertTrue(ok);
        assertEquals("^c-f", outer.toString());
    }
}