import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notAll = CharRange.isIn('a', 'z');
CharRange neg = CharRange.isNotIn('b', 'c');
assertFalse(notAll.contains(neg));
assertTrue(neg.contains('0'));
    }
}