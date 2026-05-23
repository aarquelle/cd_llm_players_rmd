import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notSmall = CharRange.isNotIn('b', 'y');
assertFalse(CharRange.isIn('a', 'z').contains(notSmall));
assertTrue(CharRange.isIn((char) 0, Character.MAX_VALUE).contains(notSmall));
    }
}