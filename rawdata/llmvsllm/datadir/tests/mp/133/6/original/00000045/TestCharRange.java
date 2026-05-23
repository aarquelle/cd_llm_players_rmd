import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a');

        String s1 = r.toString();
        assertEquals("^a-z", s1);

        boolean ok = r.contains('0');
        ok = ok & (r.contains('m') == false);
        ok = ok & r.contains(CharRange.is('m'));
        String s2 = r.toString();
        ok = ok & (s1 == s2);
        assertTrue(ok);
    }
}