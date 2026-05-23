import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'b'); // reversed; should normalize to b-d and be negated
        CharRange inner = CharRange.isNotIn('a', 'e');

        assertTrue("Negated range containment for negated ranges should honor normalized bounds",
                outer.contains(inner));
        assertEquals("toString should include negation and normalized range with dash",
                "^b-d", outer.toString());
    }
}