import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('e', 'a'); // reordered to a-e, negated
        assertEquals("^a-e", r.toString());
        assertEquals("TFFF", "" + r.contains('z') + r.contains('c') + r.contains('a') + r.contains('e'));
    }
}