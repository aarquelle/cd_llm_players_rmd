import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNegated = CharRange.isNotIn('d', 'f'); // ^d-f
        CharRange innerNegated = CharRange.isNotIn('e', 'e'); // ^e

        assertTrue(outerNegated.contains(innerNegated));
        assertFalse(CharRange.isIn((char) 0, (char) (Character.MAX_VALUE - 1)).contains(innerNegated));
    }
}