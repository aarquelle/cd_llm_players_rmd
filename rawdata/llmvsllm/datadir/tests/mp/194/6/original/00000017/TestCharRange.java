import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange negated = CharRange.isNotIn('d', 'f');
assertFalse(negated.contains(CharRange.isIn('c', 'd')));
assertFalse(negated.contains(CharRange.isIn('f', 'g')));
    }
}