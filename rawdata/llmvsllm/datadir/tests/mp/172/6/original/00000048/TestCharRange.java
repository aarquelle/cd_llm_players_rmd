import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isIn('b', 'd');
        assertEquals(83 + 'b' + 7 * 'd' + 0, r.hashCode());
        assertEquals(1, CharRange.isNotIn('b', 'd').hashCode() - r.hashCode());
    }
}