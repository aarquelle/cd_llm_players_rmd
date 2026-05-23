import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'b'); // should normalize to ^b-e
        CharRange inner = CharRange.isNotIn('c', 'd'); // ^c-d

        assertSame(outer.toString(), outer.toString());
        assertTrue(outer.contains(inner));
    }
}