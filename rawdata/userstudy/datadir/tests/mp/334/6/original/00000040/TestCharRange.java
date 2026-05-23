import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange negated = CharRange.isNotIn('c', 'f'); // everything except c-f
        assertTrue(negated.contains(CharRange.isIn('a', 'b'))); // completely outside excluded block

        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
        assertTrue(full.contains(CharRange.isNotIn('m', 'p'))); // only full range can contain a negated range
    }
}