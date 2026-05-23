import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedMiddle = CharRange.isNotIn('c', 'f'); // excludes c-f
        CharRange insideExcluded = CharRange.isIn('d', 'e');
        assertFalse(negatedMiddle.contains(insideExcluded));

        CharRange reversed = CharRange.isIn('e', 'a'); // should normalize to a-e
        assertEquals("a-e", reversed.toString());
    }
}