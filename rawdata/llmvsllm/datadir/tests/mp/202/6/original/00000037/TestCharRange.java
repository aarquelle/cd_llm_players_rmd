import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('a', 'z');
        CharRange reversed = CharRange.isIn('z', 'a');
        CharRange negated = CharRange.isNotIn('a', 'z');

        assertEquals(normalized.hashCode(), reversed.hashCode());
        assertNotEquals(normalized.hashCode(), negated.hashCode());
    }
}