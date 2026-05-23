import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg = CharRange.isNotIn('c', 'f');
CharRange overlap = CharRange.isIn('b', 'd');
assertFalse(neg.contains(overlap));
assertFalse(neg.contains(CharRange.isIn('e', 'g')));
    }
}