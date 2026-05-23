import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg = CharRange.isNotIn('d', 'f');
CharRange non = CharRange.isIn('c', 'e');
assertFalse(neg.contains(non));
assertFalse(non.contains(neg));
    }
}