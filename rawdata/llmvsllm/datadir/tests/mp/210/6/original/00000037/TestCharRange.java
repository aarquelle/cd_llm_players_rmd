import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange container = CharRange.isNotIn('c', 'e');   // everything except c-e
        assertAll(
                () -> assertTrue(container.contains(CharRange.isIn('a', 'b'))),   // entirely outside excluded block
                () -> assertFalse(container.contains(CharRange.isIn('b', 'c')))   // touches excluded block at boundary
        );
    }
}