import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('a', 'e');
        CharRange reversedSame = CharRange.isIn('e', 'a');
        CharRange negatedDifferent = CharRange.isNotIn('a', 'e');

        assertTrue(normalized.equals(reversedSame));
        assertFalse(normalized.equals(negatedDifferent));
    }
}