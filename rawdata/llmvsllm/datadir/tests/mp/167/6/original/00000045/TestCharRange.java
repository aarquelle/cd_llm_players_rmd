import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        CharRange negatedMiddle = CharRange.isNotIn('b', 'y');

        assertTrue(full.contains(negatedMiddle));
        assertTrue(CharRange.isNotIn('d', 'm').contains(CharRange.isIn('a', 'c')));
    }
}