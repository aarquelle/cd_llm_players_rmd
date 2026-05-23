import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        var range = CharRange.isIn('a', 'z');
        assertEquals(83 + 'a' + 7 * 'z' + 0, range.hashCode());
        assertEquals("a-z", range.toString());
    }
}