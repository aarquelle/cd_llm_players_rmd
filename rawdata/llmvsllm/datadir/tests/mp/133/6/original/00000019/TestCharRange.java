import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg = CharRange.isNotIn('d', 'f');
CharRange small = CharRange.isIn('g', 'h');
assertTrue(neg.contains(small));
assertFalse(neg.contains(CharRange.isIn('c', 'e')));
    }
}