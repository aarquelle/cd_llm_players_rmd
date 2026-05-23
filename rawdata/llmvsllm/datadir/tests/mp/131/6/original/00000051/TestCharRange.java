import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('z', 'a'); // should normalize to ^a-z
        assertEquals("^a-z", outer.toString());

        CharRange inner = CharRange.isIn('b', 'y'); // entirely inside a-z, so should NOT be contained by ^a-z
        assertFalse(outer.contains(inner));
    }
}