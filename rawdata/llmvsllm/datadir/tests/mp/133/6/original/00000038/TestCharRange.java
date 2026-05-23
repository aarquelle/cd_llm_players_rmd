import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('z', 'a'); // should normalize to [a-z], negated
        assertEquals("^a-z", negated.toString());
        assertTrue(negated.contains(CharRange.isIn('0', '9')));
    }
}