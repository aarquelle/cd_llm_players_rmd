import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'b'); // normalized to b-d, negated
        CharRange disjointOutside = CharRange.isIn('e', 'f');

        assertEquals("^b-d", negated.toString());
        assertTrue(negated.contains(disjointOutside));
    }
}