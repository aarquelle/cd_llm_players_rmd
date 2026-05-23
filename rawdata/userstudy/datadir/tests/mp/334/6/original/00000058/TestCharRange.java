import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange range = CharRange.isNotIn('z', 'a'); // will normalize to a-z and prepend '^'

        String first = range.toString();

        java.lang.reflect.Field f = CharRange.class.getDeclaredField("iToString");
        f.setAccessible(true);
        f.set(range, "^MUTANT");
        String second = range.toString();

        assertEquals("^a-z", first);
        assertEquals("^MUTANT", second);
    }
}