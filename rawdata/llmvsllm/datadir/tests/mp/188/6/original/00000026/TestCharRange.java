import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('a', 'c');
        assertEquals(normalized, CharRange.isIn('c', 'a'));
        assertNotEquals(normalized, CharRange.isNotIn('a', 'c'));
    }
}