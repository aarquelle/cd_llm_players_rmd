import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange notIn = CharRange.isNotIn('z', 'a'); // normalize to a-z and negated

        assertEquals("^a-z", notIn.toString());
        assertFalse(notIn.contains(CharRange.isIn('0', 'b')));
    }
}