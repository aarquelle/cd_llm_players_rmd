import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // everything except c-e
CharRange neg = CharRange.isNotIn('c', 'e');
CharRange outside = CharRange.isIn('a', 'b');
assertTrue(neg.contains(outside));
assertTrue(neg.contains('a'));
    }
}