import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notC = CharRange.isNot('c');
CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
assertTrue(full.contains(notC));
assertFalse(CharRange.isIn('a', 'z').contains(notC));
    }
}