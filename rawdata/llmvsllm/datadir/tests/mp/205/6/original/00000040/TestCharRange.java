import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // normalizes to ^a-z
        assertEquals("^a-z", r.toString());
        assertEquals("false,true,false", r.contains('a') + "," + r.contains('{' ) + "," + r.contains('m'));
    }
}