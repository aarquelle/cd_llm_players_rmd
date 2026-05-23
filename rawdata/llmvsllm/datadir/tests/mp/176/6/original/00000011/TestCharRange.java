import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange outer = CharRange.isIn('a', 'f');
CharRange neg = CharRange.isNotIn('b', 'e');
assertFalse(outer.contains(neg));
assertTrue(CharRange.isIn((char) 0, Character.MAX_VALUE).contains(neg));
    }
}