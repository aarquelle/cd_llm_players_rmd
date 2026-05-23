import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange baseNeg = CharRange.isNotIn('m', 'p');
        assertFalse(baseNeg.contains(CharRange.isIn('n', 'o')));
        assertTrue(baseNeg.contains(CharRange.isIn('a', 'b')));
    }
}