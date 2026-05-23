import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('d', 'f');
        CharRange insideGap = CharRange.isIn('e', 'e');
        assertFalse(negatedOuter.contains(insideGap));

        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedInner = CharRange.isNotIn('b', 'c');
        assertTrue(full.contains(negatedInner));
    }
}