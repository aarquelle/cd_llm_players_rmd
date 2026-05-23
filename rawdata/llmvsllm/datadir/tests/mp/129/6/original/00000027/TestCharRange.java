import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange swapped = CharRange.isIn('e', 'a'); // should normalize to a-e
        CharRange neg = CharRange.isNotIn('c', 'd');

        assertEquals("a-e", swapped.toString());
        assertTrue(neg.contains(swapped));
    }
}