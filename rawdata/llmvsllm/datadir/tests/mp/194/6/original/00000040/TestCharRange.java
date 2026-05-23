import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange outer = CharRange.isNotIn('f', 'c'); // should normalize to ^c-f
        CharRange inner = CharRange.isNotIn('b', 'g'); // should normalize to ^b-g

        assertTrue("Negated containment should require outer.start >= inner.start and outer.end <= inner.end",
                outer.contains(inner));
        assertEquals("^c-f", outer.toString());
    }
}