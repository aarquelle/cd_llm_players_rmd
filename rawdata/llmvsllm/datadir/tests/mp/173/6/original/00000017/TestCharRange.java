import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // contains everything except d..m
CharRange negated = CharRange.isNotIn('d', 'm');
assertTrue(negated.contains(CharRange.isIn('a', 'c')));
assertFalse(negated.contains(CharRange.isIn('c', 'e')));
    }
}