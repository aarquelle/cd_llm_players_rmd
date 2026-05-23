import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // contains everything except m-p
CharRange negated = CharRange.isNotIn('m', 'p');
CharRange disjoint = CharRange.isIn('a', 'c');
assertTrue(negated.contains(disjoint));
assertFalse(negated.contains(CharRange.isIn('n', 'o')));
    }
}