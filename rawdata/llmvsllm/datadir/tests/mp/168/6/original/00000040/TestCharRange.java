import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'b'); // normalized to ^b-e
        CharRange inner = CharRange.isNotIn('d', 'c'); // normalized to ^c-d

        assertTrue(outer.contains(inner));
        assertSame(outer.toString(), outer.toString());
    }
}