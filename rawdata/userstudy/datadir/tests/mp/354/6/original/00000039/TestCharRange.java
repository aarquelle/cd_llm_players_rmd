import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange allExceptA = CharRange.isNot('a');
        CharRange allExceptB = CharRange.isNot('b');
        assertFalse(allExceptA.contains(allExceptB));

        CharRange notA = CharRange.isNot('a');
        CharRange bc = CharRange.isIn('b', 'c');
        assertTrue(notA.contains(bc));
    }
}