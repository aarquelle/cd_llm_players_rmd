import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('b', 'd'); // start=98, end=100, negated=true
        assertEquals(83 + 'b' + 7 * 'd' + 1, r.hashCode());
    }
}