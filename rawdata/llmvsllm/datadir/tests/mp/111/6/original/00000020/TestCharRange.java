import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notFull = CharRange.isIn((char) 0, (char) (Character.MAX_VALUE - 1));
CharRange neg = CharRange.isNotIn('a', 'c');
assertFalse(notFull.contains(neg));
    }
}