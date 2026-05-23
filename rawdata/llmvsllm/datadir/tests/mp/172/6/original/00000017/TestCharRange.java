import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // everything except c-d
CharRange neg = CharRange.isNotIn('c', 'd');
// disjoint from c-d
CharRange range = CharRange.isIn('a', 'b');
assertTrue(neg.contains(range));
assertFalse(neg.contains(CharRange.isIn('b', 'c')));
    }
}