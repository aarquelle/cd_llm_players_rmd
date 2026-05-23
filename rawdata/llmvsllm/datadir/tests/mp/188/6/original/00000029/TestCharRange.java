import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange normalized = CharRange.isIn('a', 'z');
        CharRange swapped = CharRange.isIn('z', 'a');
        assertEquals(83 + 'a' + 7 * 'z' + 0, normalized.hashCode());
        assertEquals(normalized.hashCode(), swapped.hashCode());
    }
}