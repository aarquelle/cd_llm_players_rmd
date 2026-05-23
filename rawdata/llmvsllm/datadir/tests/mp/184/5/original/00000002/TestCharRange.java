import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNot('x');

        java.lang.reflect.Field f = CharRange.class.getDeclaredField("negated");
        f.setAccessible(true);

        assertEquals(((Boolean) f.get(r)).booleanValue(), r.isNegated());
    }
}