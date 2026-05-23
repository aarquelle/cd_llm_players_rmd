import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'b'); // normalizes to ^b-e

        boolean ok1 = outer.contains(CharRange.isIn('a', 'a'));
        boolean ok2 = outer.contains(CharRange.isIn('c', 'd'));

        assertTrue(ok1);
        assertFalse(ok2);
    }
}