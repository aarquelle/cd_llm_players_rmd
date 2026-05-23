import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // should reorder to a-z and include '^'
        String s1 = r.toString();

        java.lang.reflect.Field f = CharRange.class.getDeclaredField("iToString");
        f.setAccessible(true);
        String cached = (String) f.get(r);

        assertEquals("^a-z", s1);
        assertSame(s1, cached);
    }
}