import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isIn('a', 'd');
        assertEquals(83 + 'a' + 7 * 'd', r.hashCode());
        assertNotEquals(r.hashCode(), CharRange.isNotIn('a', 'd').hashCode());
    }
}