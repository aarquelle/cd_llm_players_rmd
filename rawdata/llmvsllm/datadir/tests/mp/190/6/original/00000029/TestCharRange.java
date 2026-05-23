import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'b'); // reordered -> b-d, negated
        CharRange inner = CharRange.isIn('b', 'c');

        assertFalse(negated.contains(inner));
        assertEquals("^b-d", negated.toString());
    }
}