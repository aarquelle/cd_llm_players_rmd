import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negOuter = CharRange.isNotIn('d', 'l');
        CharRange negInner = CharRange.isNotIn('c', 'm');

        assertTrue(negOuter.contains(negInner));
        assertFalse(negInner.contains(negOuter));
    }
}