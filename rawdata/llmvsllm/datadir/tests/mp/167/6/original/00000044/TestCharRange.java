import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('b', 'y');
        CharRange innerNegWider = CharRange.isNotIn('a', 'z');
        assertTrue(outerNeg.contains(innerNegWider));

        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        assertTrue(full.contains(CharRange.isNotIn('m', 'n')));
    }
}