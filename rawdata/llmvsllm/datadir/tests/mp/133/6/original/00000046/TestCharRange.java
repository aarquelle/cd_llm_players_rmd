import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange r = CharRange.isNotIn('z', 'a'); // normalize to a-z, negated
        assertEquals("^a-z", r.toString());

        StringBuilder sb = new StringBuilder();
        sb.append(r.contains('a'));
        sb.append(',');
        sb.append(r.contains('m'));
        sb.append(',');
        sb.append(r.contains('z'));
        sb.append(',');
        sb.append(r.contains('0'));
        sb.append(',');
        sb.append(r.contains('{'));

        assertEquals("false,false,false,true,true", sb.toString());
    }
}