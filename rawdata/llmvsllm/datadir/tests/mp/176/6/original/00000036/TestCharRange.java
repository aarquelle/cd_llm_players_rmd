import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('e', 'a'); // should reorder to a-e and negate
        assertEquals("^a-e", neg.toString());

        CharRange mid = CharRange.isIn('b', 'd');
        assertTrue(neg.contains(mid));
    }
}