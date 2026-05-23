import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'a'); // reversed inputs; should normalize to a-e and be negated
        CharRange inner = CharRange.isNotIn('b', 'd');

        assertTrue(outer.contains(inner));
        assertEquals("^a-e", outer.toString());
    }
}