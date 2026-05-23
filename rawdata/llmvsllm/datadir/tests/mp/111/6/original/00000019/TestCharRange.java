import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange full = CharRange.isIn((char) 0, Character.MAX_VALUE);
CharRange neg = CharRange.isNotIn('a', 'c');
assertTrue(full.contains(neg));
    }
}