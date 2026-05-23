import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('e', 'a');
        assertEquals("a-e", normalized.toString());

        CharRange neg = CharRange.isNotIn('c', 'd');
        assertTrue(neg.contains(CharRange.isIn('a', 'b')));
    }
}