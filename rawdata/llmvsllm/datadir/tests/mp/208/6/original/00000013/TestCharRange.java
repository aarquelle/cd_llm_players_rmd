import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange negated = CharRange.isNotIn('c', 'f');
CharRange overlapping = CharRange.isIn('b', 'd');
assertFalse(negated.contains(overlapping));
assertTrue(negated.contains('a'));
    }
}