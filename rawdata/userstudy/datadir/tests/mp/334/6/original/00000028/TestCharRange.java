import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('d', 'f'); // all except d-f
        CharRange nonNegatedInner = CharRange.isIn('e', 'e'); // inside excluded section -> should NOT be contained

        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedInner = CharRange.isNotIn('m', 'n'); // should be contained only by full range

        assertFalse(negatedOuter.contains(nonNegatedInner));
        assertTrue(full.contains(negatedInner));
    }
}