import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedMiddle = CharRange.isNotIn('c', 'f'); // everything except [c-f]
        CharRange insideExcluded = CharRange.isIn('d', 'e');   // fully within excluded part
        assertFalse(negatedMiddle.contains(insideExcluded));

        CharRange universal = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedSingle = CharRange.isNot('x');
        assertTrue(universal.contains(negatedSingle));
    }
}