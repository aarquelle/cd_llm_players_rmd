import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedMiddle = CharRange.isNotIn('c', 'e'); // excludes c..e
        CharRange outsideGap = CharRange.isIn('a', 'b');       // entirely outside excluded gap
        CharRange touchingGap = CharRange.isIn('b', 'c');      // touches excluded gap at 'c'

        assertTrue(negatedMiddle.contains(outsideGap));
        assertFalse(negatedMiddle.contains(touchingGap));
    }
}