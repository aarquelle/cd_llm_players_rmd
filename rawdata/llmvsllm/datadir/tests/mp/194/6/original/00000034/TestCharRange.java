import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'a'); // normalized to ^a-e
        CharRange inner = CharRange.isIn('b', 'd');

        assertEquals("^a-e", outer.toString());

        String first = outer.toString();
        String second = outer.toString();
        assertSame(first, second);
    }
}