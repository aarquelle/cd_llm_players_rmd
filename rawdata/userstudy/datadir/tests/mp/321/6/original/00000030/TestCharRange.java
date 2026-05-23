import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedSingle = CharRange.isNot('x');

        CharRange negatedOuter = CharRange.isNotIn('d', 'f');
        CharRange negatedInnerWider = CharRange.isNotIn('b', 'h');

        assertTrue(full.contains(negatedSingle));
        assertFalse(negatedOuter.contains(negatedInnerWider));
    }
}