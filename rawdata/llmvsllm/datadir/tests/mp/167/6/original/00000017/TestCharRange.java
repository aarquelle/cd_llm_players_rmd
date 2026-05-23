import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg = CharRange.isNotIn('c', 'f');
CharRange disjoint = CharRange.isIn('g', 'h');
assertTrue(neg.contains(disjoint));
assertFalse(neg.contains(CharRange.isIn('f', 'g')));
    }
}