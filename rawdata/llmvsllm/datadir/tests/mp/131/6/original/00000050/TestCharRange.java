import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'a'); // constructor should normalize to a-e and keep negated
        assertTrue(outer.contains(CharRange.isIn('f', 'g')));

        assertEquals("^a-e", outer.toString());
    }
}