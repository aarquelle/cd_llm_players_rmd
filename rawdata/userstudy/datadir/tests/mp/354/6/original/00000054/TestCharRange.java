import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('d', 'f');
        CharRange outside = CharRange.isIn('a', 'c');
        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);

        assertTrue(negated.contains(outside));
        assertTrue(full.contains(negated));
    }
}