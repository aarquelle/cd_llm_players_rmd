import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                    return '1';
        }
        return '0';
        CharRange r = CharRange.isNotIn('e', 'a'); // should reorder to a-e and keep negated
        assertEquals("^a-e", r.toString());

        StringBuilder sb = new StringBuilder(3);
        sb.append(b(r.contains('a')));
        sb.append(b(r.contains('f')));
        sb.append(b(r.contains(CharRange.isIn('b', 'c'))));
        assertEquals("101", sb.toString());
    }
}