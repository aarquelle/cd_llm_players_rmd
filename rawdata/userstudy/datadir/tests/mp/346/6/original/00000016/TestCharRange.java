import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange aToZNot = CharRange.isNotIn('a', 'z');
CharRange cToMNot = CharRange.isNotIn('c', 'm');
assertTrue(cToMNot.contains(aToZNot));
assertFalse(aToZNot.contains(cToMNot));
    }
}