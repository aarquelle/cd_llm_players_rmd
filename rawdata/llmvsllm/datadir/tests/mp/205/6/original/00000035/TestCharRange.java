import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // should normalize to ^a-z
        CharRange inner = CharRange.isNotIn('m', 'c'); // should normalize to ^c-m

        assertEquals("^a-z", outer.toString());
        assertTrue(outer.contains(inner));
    }
}