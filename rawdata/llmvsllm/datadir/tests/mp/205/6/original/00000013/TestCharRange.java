import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange wider = CharRange.isNotIn('c', 'g');
CharRange narrower = CharRange.isNotIn('d', 'f');
assertTrue(narrower.contains(wider));
assertFalse(wider.contains(narrower));
    }
}