import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // normalized to ^a-z
        CharRange inner = CharRange.isNotIn('c', 'x'); // normalized to ^c-x

        String s1 = outer.toString();
        String s2 = outer.toString();

        assertSame(s1, s2);
        assertTrue(outer.contains(inner));
    }
}