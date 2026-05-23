import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'f');
        CharRange inner = CharRange.isNotIn('f', 'd'); // reversed input, should normalize to same as outer

        assertTrue(outer.contains(inner));
        assertSame(outer.toString(), outer.toString());
    }
}