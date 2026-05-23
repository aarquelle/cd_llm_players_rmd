import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isIn('e', 'a'); // will be normalized to a-e
        assertEquals(83 + 'a' + 7 * 'e', range.hashCode());
        assertEquals(CharRange.isIn('a', 'e').hashCode(), range.hashCode());
    }
}