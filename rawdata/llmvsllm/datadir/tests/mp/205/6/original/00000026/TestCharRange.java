import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('d', 'b'); // normalizes to ^b-d
        assertEquals("^b-d", outer.toString());
        assertEquals("true,false", outer.contains(CharRange.is('a')) + "," + outer.contains(CharRange.is('c')));
    }
}