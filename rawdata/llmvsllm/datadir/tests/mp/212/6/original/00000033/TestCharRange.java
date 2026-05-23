import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'c'); // reversed; should normalize to c-e, negated => ^c-e
        CharRange inner = CharRange.isNotIn('d', 'd'); // ^d

        assertTrue(outer.contains(inner));
        assertEquals("^c-e", outer.toString());
    }
}