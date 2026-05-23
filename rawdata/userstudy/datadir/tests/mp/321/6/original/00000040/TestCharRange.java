import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedMiddle = CharRange.isNotIn('c', 'f'); // excludes c..f
        CharRange outside = CharRange.isIn('a', 'b');          // fully outside excluded block -> should be contained
        assertTrue(negatedMiddle.contains(outside));

        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedSingle = CharRange.isNot('x');
        assertTrue(full.contains(negatedSingle));
    }
}