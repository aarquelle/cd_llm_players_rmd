import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('f', 'b'); // should normalize to [b-f] negated
        CharRange inner = CharRange.isIn('c', 'd');           // inside [b-f]

        assertFalse(outerNegated.contains(inner));
        assertEquals("^b-f", outerNegated.toString());
    }
}