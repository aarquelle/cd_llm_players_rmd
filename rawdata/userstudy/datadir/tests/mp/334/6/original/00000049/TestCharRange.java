import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalSwapped = CharRange.isIn('e', 'a'); // normalizes to a-e
        CharRange negatedSame = CharRange.isNotIn('a', 'e');

        assertEquals(83 + 'a' + 7 * 'e' + 0, normalSwapped.hashCode());
        assertEquals(83 + 'a' + 7 * 'e' + 1, negatedSame.hashCode());
    }
}