import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange negWide = CharRange.isNotIn('c', 'g');
CharRange negNarrow = CharRange.isNotIn('d', 'f');
assertTrue(negWide.contains(negNarrow));
assertFalse(negNarrow.contains(negWide));
    }
}