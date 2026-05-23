import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('e', 'a'); // should normalize to a-e
        assertAll(
                () -> assertEquals(83 + 'a' + 7 * 'e', normalized.hashCode()),
                () -> assertNotEquals(83 + 'a' + 7 * 'e', CharRange.isNotIn('a', 'e').hashCode())
        );
    }
}