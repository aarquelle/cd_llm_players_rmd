import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // should reverse to a-z and be negated
        assertTrue(r.contains(CharRange.isIn('1', '9')));
        assertSame(r.toString(), r.toString());
    }
}