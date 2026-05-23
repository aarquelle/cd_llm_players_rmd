import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('e', 'c'); // normalized to c-e, negated
        CharRange innerPosOutside = CharRange.isIn('a', 'a'); // outside c-e => should be contained by negated range

        CharRange outerPos = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange innerNeg = CharRange.isNot('x');

        assertTrue(outerNeg.contains(innerPosOutside));
        assertTrue(outerPos.contains(innerNeg));
    }
}