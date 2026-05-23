import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange thisRange = CharRange.isNotIn('m', 'p');
// overlaps excluded area
CharRange other = CharRange.isIn('l', 'n');
assertFalse(thisRange.contains(other));
assertTrue(thisRange.contains(CharRange.isIn('a', 'l')));
    }
}