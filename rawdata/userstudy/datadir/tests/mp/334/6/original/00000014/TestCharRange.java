import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // contains everything except d-f
CharRange neg = CharRange.isNotIn('d', 'f');
CharRange disjointLeft = CharRange.isIn('a', 'c');
assertTrue(neg.contains(disjointLeft));
assertFalse(neg.contains(CharRange.isIn('e', 'e')));
    }
}