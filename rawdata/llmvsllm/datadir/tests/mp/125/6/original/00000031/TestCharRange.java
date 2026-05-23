import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outerNeg = CharRange.isNotIn('e', 'c'); // should normalize to c-e, negated
        CharRange innerPosInside = CharRange.isIn('d', 'd');
        CharRange outerPos = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange innerNeg = CharRange.isNot('x');

        assertTrue(outerNeg.contains(innerPosInside));
        assertTrue(outerPos.contains(innerNeg));
    }
}