import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isIn('z', 'a');      // reordered to a-z
        CharRange negated = CharRange.isNotIn('a', 'z'); // same bounds but negated

        assertEquals(83 + 'a' + 7 * 'z', range.hashCode());
        assertEquals(range.hashCode() + 1, negated.hashCode());
    }
}