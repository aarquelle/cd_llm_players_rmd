import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notA = CharRange.isNot('a');
CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
assertTrue(full.contains(notA));
assertFalse(CharRange.isIn('a', 'z').contains(notA));
    }
}