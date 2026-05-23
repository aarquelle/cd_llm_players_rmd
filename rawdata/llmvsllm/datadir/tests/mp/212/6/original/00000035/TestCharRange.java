import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'c'); // normalized to ^c-e
        CharRange inner = CharRange.isNotIn('c', 'e'); // same normalized range

        assertTrue(outer.contains(inner));
        assertEquals("^c-e", outer.toString());
    }
}