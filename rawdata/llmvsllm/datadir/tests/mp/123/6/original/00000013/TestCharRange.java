import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        // contains everything except c-e
CharRange negOuter = CharRange.isNotIn('c', 'e');
CharRange disjoint = CharRange.isIn('a', 'b');
assertTrue(negOuter.contains(disjoint));
assertFalse(negOuter.contains(CharRange.isIn('d', 'd')));
    }
}