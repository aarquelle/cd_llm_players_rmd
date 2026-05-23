import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // excludes c-e
CharRange neg = CharRange.isNotIn('c', 'e');
CharRange overlapping = CharRange.isIn('b', 'd');
assertFalse(neg.contains(overlapping));
assertFalse(neg.contains('d'));
    }
}