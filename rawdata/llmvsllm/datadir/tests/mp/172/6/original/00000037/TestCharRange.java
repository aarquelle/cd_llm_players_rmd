import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // excludes c-d
CharRange thisRange = CharRange.isNotIn('c', 'd');
// excludes b-e (superset exclusion)
CharRange other = CharRange.isNotIn('b', 'e');
assertTrue(thisRange.contains(other));
assertFalse(other.contains(thisRange));
    }
}