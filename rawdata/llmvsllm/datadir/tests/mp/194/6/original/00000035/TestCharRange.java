import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'b'); // should normalize to b-d and be negated
        CharRange inside = CharRange.isIn('b', 'c');
        assertEquals("^b-d", negated.toString());
        assertFalse(negated.contains(inside));
    }
}