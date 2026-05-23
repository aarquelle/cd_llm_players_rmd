import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange all = CharRange.isIn((char) 0, Character.MAX_VALUE);
CharRange neg = CharRange.isNotIn('c', 'e');
assertTrue(all.contains(neg));
assertTrue(all.contains(CharRange.isNot('x')));
    }
}