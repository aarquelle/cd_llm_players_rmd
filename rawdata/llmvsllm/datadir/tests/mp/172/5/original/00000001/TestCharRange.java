import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isIn('a', 'c');

        String first = range.toString();
        java.lang.reflect.Field f = CharRange.class.getDeclaredField("iToString");
        f.setAccessible(true);
        String cached = (String) f.get(range);

        assertSame(first, cached);
    }
}