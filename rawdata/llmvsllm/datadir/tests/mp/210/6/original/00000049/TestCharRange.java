import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negatedOuter = CharRange.isNotIn('d', 'b'); // normalizes to ^b-d
        CharRange negatedInner = CharRange.isNotIn('c', 'a'); // normalizes to ^a-c

        assertFalse(negatedOuter.contains(negatedInner));
        assertEquals("^b-d", negatedOuter.toString());
    }
}