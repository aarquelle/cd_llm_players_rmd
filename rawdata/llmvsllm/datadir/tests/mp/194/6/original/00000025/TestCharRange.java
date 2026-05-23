import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'm'); // excludes d-m
        CharRange innerBoundary = CharRange.isNotIn('d', 'm'); // same exclusions -> should be contained
        CharRange innerInternal = CharRange.isNotIn('e', 'l');  // smaller exclusion -> should NOT be contained

        assertAll(
                () -> assertTrue(outer.contains(innerBoundary)),
                () -> assertFalse(outer.contains(innerInternal))
        );
    }
}