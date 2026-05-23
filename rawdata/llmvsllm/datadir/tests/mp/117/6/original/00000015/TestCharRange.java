import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // contains everything except c-f
CharRange neg = CharRange.isNotIn('c', 'f');
// entirely outside excluded region
CharRange other = CharRange.isIn('a', 'b');
assertTrue(neg.contains(other));
assertFalse(other.contains(neg));
    }
}