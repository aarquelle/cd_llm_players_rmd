import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedMiddle = CharRange.isNotIn('d', 'f'); // everything except d..f
        assertFalse(negatedMiddle.contains(CharRange.isIn('e', 'e'))); // inside excluded block
        assertTrue(negatedMiddle.contains(CharRange.isIn('a', 'c')));  // completely outside excluded block
    }
}