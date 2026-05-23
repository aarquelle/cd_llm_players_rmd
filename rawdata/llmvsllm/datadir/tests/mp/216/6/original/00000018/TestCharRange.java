import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange nonNeg = CharRange.isIn('a', 'z');
CharRange neg = CharRange.isNotIn('c', 'e');
assertFalse(nonNeg.contains(neg));
assertFalse(CharRange.isIn((char) 0, Character.MAX_VALUE).contains(neg) == false);
    }
}