import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange thisRange = CharRange.isNotIn('c', 'f');
CharRange other = CharRange.isNotIn('d', 'e');
assertFalse(thisRange.contains(other));
    }
}