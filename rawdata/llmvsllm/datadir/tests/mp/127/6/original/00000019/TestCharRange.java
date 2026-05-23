import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange notFull = CharRange.isIn('a', 'z');
CharRange neg = CharRange.isNotIn('d', 'f');
assertFalse(notFull.contains(neg));
assertTrue(notFull.contains('m'));
    }
}