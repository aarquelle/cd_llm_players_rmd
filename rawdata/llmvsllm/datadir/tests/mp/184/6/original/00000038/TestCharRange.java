import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // normalized to [a-z], negated
        CharRange inside = CharRange.isIn('b', 'y');

        assertFalse(outer.contains(inside));
        assertEquals("^a-z", outer.toString());
    }
}