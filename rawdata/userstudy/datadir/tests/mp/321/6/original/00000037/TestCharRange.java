import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCharRange {
    @Test(timeout = 4000)
    public void test() throws Throwable {
                CharRange container = CharRange.isNotIn('d', 'f'); // everything except d-f
        CharRange inner = CharRange.isIn('e', 'g');         // overlaps excluded part (e-f)

        assertFalse(container.contains(inner));
    }
}