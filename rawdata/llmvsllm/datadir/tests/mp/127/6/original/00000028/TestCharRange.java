import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('z', 'a'); // should normalize to start='a', end='z', negated=true
        int expected = 83 + 'a' + 7 * 'z' + 1;
        assertEquals(expected, range.hashCode());
    }
}