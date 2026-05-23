import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange negSmall = CharRange.isNotIn('c', 'd');
CharRange negLarge = CharRange.isNotIn('b', 'e');
assertTrue(negSmall.contains(negLarge));
assertFalse(negLarge.contains(negSmall));
    }
}