import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange pos = CharRange.isIn('a', 'e');
CharRange neg = CharRange.isNotIn('a', 'e');
assertNotEquals(pos.hashCode(), neg.hashCode());
assertFalse(pos.equals(neg));
    }
}