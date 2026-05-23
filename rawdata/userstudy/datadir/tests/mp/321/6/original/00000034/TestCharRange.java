import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange container = CharRange.isNotIn('c', 'f'); // everything except c-f
        CharRange candidate = CharRange.isIn('b', 'g');    // spans both outside and inside excluded area

        assertFalse(container.contains(candidate));
    }
}