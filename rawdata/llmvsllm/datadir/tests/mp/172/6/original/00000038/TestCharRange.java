import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange thisRange = CharRange.isNotIn('m', 'p');
CharRange other = CharRange.isIn('a', 'l');
assertTrue(thisRange.contains(other));
assertFalse(other.contains(thisRange));
    }
}