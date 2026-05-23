import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'f'); // excludes d..f

        assertEquals(CharRange.isIn('a', 'e').toString(), CharRange.isIn('e', 'a').toString());
        assertTrue(negated.contains(CharRange.isIn('a', 'c')) == true);
    }
}