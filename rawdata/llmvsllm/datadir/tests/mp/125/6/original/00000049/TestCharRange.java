import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('e', 'a'); // reversed; should normalize to a-e, negated
        CharRange inner = CharRange.isNotIn('c', 'c'); // negated single char

        assertEquals("^a-e", outer.toString());
        assertFalse(outer.contains(inner));
    }
}