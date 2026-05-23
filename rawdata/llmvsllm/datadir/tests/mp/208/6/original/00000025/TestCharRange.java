import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedMiddle = CharRange.isNotIn('c', 'e'); // everything except c-e
        CharRange insideGap = CharRange.isIn('d', 'd');        // within excluded region
        CharRange outsideGap = CharRange.isIn('b', 'b');       // outside excluded region

        assertFalse(negatedMiddle.contains(insideGap));
        assertTrue(negatedMiddle.contains(outsideGap));
    }
}