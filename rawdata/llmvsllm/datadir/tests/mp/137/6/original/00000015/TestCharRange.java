import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // excludes c-f
CharRange negOuter = CharRange.isNotIn('c', 'f');
// excludes b-g (wider)
CharRange negInner = CharRange.isNotIn('b', 'g');
assertTrue(negOuter.contains(negInner));
assertFalse(negInner.contains(negOuter));
    }
}