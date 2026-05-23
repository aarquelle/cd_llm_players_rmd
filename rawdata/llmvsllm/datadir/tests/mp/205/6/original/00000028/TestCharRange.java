import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'a');
        CharRange inner = CharRange.isNotIn('b', 'c');

        assertTrue(outer.contains(inner));

        String s1 = outer.toString();
        String s2 = outer.toString();
        assertTrue(s1 == s2);
    }
}