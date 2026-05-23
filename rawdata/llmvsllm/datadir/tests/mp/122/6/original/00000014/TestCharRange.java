import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
        CharRange neg = CharRange.isNotIn('c', 'e');
CharRange overlaps = CharRange.isIn('b', 'd');
assertFalse(neg.contains(overlaps));
assertFalse(neg.contains(CharRange.isIn('e', 'f')));
    }
}