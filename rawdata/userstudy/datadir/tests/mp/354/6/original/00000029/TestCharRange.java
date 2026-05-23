import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange positiveOutside = CharRange.isIn('a', 'b');
        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedSingle = CharRange.isNot('x');

        assertTrue(negated.contains(positiveOutside));
        assertTrue(full.contains(negatedSingle));
    }
}