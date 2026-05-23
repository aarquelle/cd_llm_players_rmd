import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'a'); // should normalize to ^a-e
        CharRange inner = CharRange.isNotIn('b', 'd'); // within a-e, negated

        assertFalse(outer.contains(inner));
        assertEquals("^a-e", outer.toString());
    }
}