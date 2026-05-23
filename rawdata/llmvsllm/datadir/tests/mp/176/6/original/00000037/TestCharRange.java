import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange neg = CharRange.isNotIn('e', 'a'); // reorder to a-e then negate
        assertEquals("^a-e", neg.toString());

        CharRange outside = CharRange.isIn('x', 'z');
        assertTrue(neg.contains(outside));
    }
}