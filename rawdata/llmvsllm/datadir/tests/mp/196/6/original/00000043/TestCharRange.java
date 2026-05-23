import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('e', 'a'); // should normalize to a-e, negated
        CharRange innerNegated = CharRange.isNotIn('d', 'b'); // should normalize to b-d, negated

        assertTrue(negated.contains(innerNegated));
        assertEquals("^a-e", negated.toString());
    }
}