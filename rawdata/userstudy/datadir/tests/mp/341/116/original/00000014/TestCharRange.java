import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        var range = CharRange.isIn('a', 'z');
        assertTrue(range.equals(range));
        assertFalse(CharRange.is('a').equals('a'));
    }
}