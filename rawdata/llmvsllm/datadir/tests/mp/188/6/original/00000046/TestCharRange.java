import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNot('a');
        assertAll(
                () -> assertTrue(range.isNegated(), "isNot must create a negated range"),
                () -> assertEquals("^a", range.toString(), "negated single-char range must render with '^' prefix")
        );
    }
}