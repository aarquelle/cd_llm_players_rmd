import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange outer = CharRange.isIn((char) 0, Character.MAX_VALUE);
CharRange negated = CharRange.isNot('x');
assertTrue(outer.contains(negated));
assertFalse(CharRange.isIn('a', 'z').contains(negated));
    }
}