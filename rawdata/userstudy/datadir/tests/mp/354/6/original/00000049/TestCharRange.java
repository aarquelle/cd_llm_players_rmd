import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('a', 'e');
        assertAll(
                () -> assertEquals(normalized, CharRange.isIn('e', 'a')),
                () -> assertNotEquals(normalized, CharRange.isNotIn('e', 'a'))
        );
    }
}