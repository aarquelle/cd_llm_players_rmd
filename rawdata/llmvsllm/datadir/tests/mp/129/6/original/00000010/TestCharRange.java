import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg = CharRange.isNotIn('m', 'p');
CharRange disjoint = CharRange.isIn('a', 'c');
assertTrue(neg.contains(disjoint));
assertFalse(neg.contains(CharRange.isIn('n', 'o')));
    }
}