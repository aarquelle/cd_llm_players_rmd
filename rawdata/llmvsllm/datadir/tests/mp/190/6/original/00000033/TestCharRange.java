import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isNotIn('z', 'a'); // should normalize to ^a-z
        CharRange disjoint = CharRange.isIn('0', '9'); // outside a-z

        assertEquals("^a-z", normalized.toString());
        assertTrue(normalized.contains(disjoint));
    }
}