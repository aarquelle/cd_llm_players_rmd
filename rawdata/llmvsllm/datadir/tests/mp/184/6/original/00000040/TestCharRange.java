import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a');

        String s1 = r.toString();
        String s2 = r.toString();

        assertSame(s1, s2);
        assertEquals("^a-z|false|true", s1 + "|" + r.contains('m') + "|" + r.contains('{'));
    }
}